package ua.com.deluxedostavka.service.impl;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.deluxedostavka.dto.goods.GoodsForOrderRequest;
import ua.com.deluxedostavka.dto.goods.GoodsForOrderResponse;
import ua.com.deluxedostavka.dto.order.OrderRequest;
import ua.com.deluxedostavka.dto.order.OrderResponse;
import ua.com.deluxedostavka.dto.other.DataResponse;
import ua.com.deluxedostavka.dto.other.PaginationRequest;
import ua.com.deluxedostavka.dto.user.UserRequest;
import ua.com.deluxedostavka.dto.user.UserResponse;
import ua.com.deluxedostavka.entity.Goods;
import ua.com.deluxedostavka.entity.GoodsForOrder;
import ua.com.deluxedostavka.entity.Order;
import ua.com.deluxedostavka.entity.User;
import ua.com.deluxedostavka.exception.ObjectNotFoundException;
import ua.com.deluxedostavka.exception.OrderException;
import ua.com.deluxedostavka.repository.GoodsForOrderRepository;
import ua.com.deluxedostavka.repository.GoodsRepository;
import ua.com.deluxedostavka.repository.OrderRepository;
import ua.com.deluxedostavka.repository.UserRepository;
import ua.com.deluxedostavka.service.OrderService;
import ua.com.deluxedostavka.tools.SmsSender;
import ua.com.deluxedostavka.tools.TelegramTool;

import javax.xml.bind.JAXBException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log
public class OrderServiceImpl implements OrderService{


    private static final String TELEGRAM_URL = "https://api.telegram.org/bot650088115:AAH5P9G_WZjT5swP9KQsMfO3d9taajc5uxI/sendMessage?chat_id=-313101769&text=";

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private GoodsForOrderRepository goodsForOrderRepository;

    @Autowired
    private SmsSender smsSender;

    @Autowired
    private TelegramTool telegramTool;


    @Override
    @Transactional
    public OrderResponse order(OrderRequest orderRequest) throws ObjectNotFoundException, JAXBException {
        log.info("Order : "+orderRequest.getUser().getNumber());
        Order order = new Order();
        order.setDescription(orderRequest.getDescription());
        order.setArchive(false);
        order.setDate(LocalDateTime.now(ZoneId.systemDefault()));
        order = orderRepository.saveAndFlush(order);
        Long sum = createGoodsForOrder(orderRequest.getGoods(),order);
        order.setSum(sum);
        sum/=100;
        sum+=15;//add price of box
//        smsSender.sendSms(orderRequest.getUser().getName(),orderRequest.getUser().getNumber(),sum);
        order.setUser(createUser(orderRequest.getUser(),order));//test
        order = orderRepository.saveAndFlush(order);
        order.setGoodsForOrders(goodsForOrderRepository.findByOrderId(order.getId()));
        OrderResponse orderResponse =  new OrderResponse(order);
//        telegramTool.sendNotification(TELEGRAM_URL+createMessageForTelegram(orderResponse,sum));
        return orderResponse;
    }


    private String createMessageForTelegram(OrderResponse orderResponse, Long sum){
        String text = "Нове замовлення : \n";
        UserResponse user = orderResponse.getUserResponse();
        text+="Ім'я замовника : "+user.getName()+ " " +user.getSurName()+"\n";
        text+="Номер телефону : "+user.getNumber()+"\n";
        text+="Адреса доставки : "+user.getAddress()+ " " +user.getAddressNumber() + "\n";
        text+="Коментар : "+ orderResponse.getDescription()+"\n";
        text+="Дата/час : "+ orderResponse.getDate()+"\n";
        text+="Сума замовлення : "+sum+"\n";
        String dishes = "\n";
        for (GoodsForOrderResponse goodsResponseList : orderResponse.getGoodsResponseList()){
            dishes+="Нзава: "+goodsResponseList.getName()+ " Ціна : " + (goodsResponseList.getPrice()/100) + " Кількість : " + goodsResponseList.getCount()+"\n";
            dishes+="------------------------------------------------------\n";
        }
        dishes+="";
        text+="Замовлення : \nУпаковака 15 грн\n"+dishes;
        return text;
    }

    @Override
    @Transactional
    public OrderResponse updateOrder(OrderRequest orderRequest) throws ObjectNotFoundException, OrderException {
        Order order = orderRepository.findOne(orderRequest.getId());
        if(order!=null){
            goodsForOrderRepository.delete(order.getGoodsForOrders());
            Long sum = createGoodsForOrder(orderRequest.getGoods(),order);
            order.setSum(sum);
            return new OrderResponse(order);
        }else{
            throw new OrderException("Order for update not found");
        }
    }

    @Override
    @Transactional
    public List<OrderResponse> getAll() {
        return orderRepository.findAll().stream().map(OrderResponse::new).collect(Collectors.toList());
    }

    @Override
    public OrderResponse archived(Long id) {
        Order order =orderRepository.findOne(id);
        order.setArchive(true);
        return new OrderResponse(orderRepository.saveAndFlush(order));
    }

    @Override
    public OrderResponse unarchived(Long id) {
        Order order =orderRepository.findOne(id);
        order.setArchive(false);
        return new OrderResponse(orderRepository.saveAndFlush(order));
    }

    @Override
    public DataResponse<OrderResponse> getAll(PaginationRequest paginationRequest) {
        Page<Order> page = orderRepository.findAll(paginationRequest.toPageRequest());
        return new DataResponse<>(page.getContent().stream().map(OrderResponse::new).collect(Collectors.toList()), page.getTotalElements());
    }

    @Override
    public DataResponse<OrderResponse> getAllByArchived(PaginationRequest paginationRequest, Boolean archived) {
        Page<Order> page = orderRepository.findByArchive(archived, paginationRequest.toPageRequest());
        return new DataResponse<>(page.getContent().stream().map(OrderResponse::new).collect(Collectors.toList()), page.getTotalElements(), page.getTotalPages());
    }

    @Override
    public void delete(Long id) {
        orderRepository.delete(id);
    }

    @Override
    public DataResponse<OrderResponse> searchOrders(String value, Integer page, Integer size, String sortBy, Sort.Direction direction) {
        PageRequest pageRequest;
        if(sortBy != null && direction != null) {
            Sort sort = new Sort(direction, sortBy);
            pageRequest = new PageRequest(page, size, sort);
        }else{
            pageRequest = new PageRequest(page, size);
        }
        Page<Order> orderPage = orderRepository.findByUserNameOrUserSurNameOrUserNumber(value,pageRequest);
        return new DataResponse<>(orderPage.getContent().stream().map(OrderResponse::new).collect(Collectors.toList()), orderPage.getTotalElements(),orderPage.getTotalPages());
    }

    private Long createGoodsForOrder(List<GoodsForOrderRequest> goodsForOrderRequestList, Order order) throws ObjectNotFoundException {
        Long sum = 0L;
        for (GoodsForOrderRequest request : goodsForOrderRequestList) {
            GoodsForOrder goodsForOrder = new GoodsForOrder();
            goodsForOrder.setCount(request.getCount());
            Goods goods = findOne(request.getIdGoods());
            sum += (goods.getPrice()*request.getCount());
            goodsForOrder.setGoods(goods);
            goodsForOrder.setOrder(order);
            goodsForOrderRepository.saveAndFlush(goodsForOrder);
        }
        return sum;
    }

    private User createUser(UserRequest userRequest,Order order) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setSurName(userRequest.getSurName());
        user.setAddress(userRequest.getAddress());
        user.setAddressNumber(userRequest.getAddressNumber());
        user.setNumber(userRequest.getNumber());
        user.setOrder(order);
        return userRepository.saveAndFlush(user);
    }

    private Goods findOne(Long id) throws ObjectNotFoundException {
        Goods goods = goodsRepository.findOne(id);
        if(goods != null){
            return goods;
        }else{
            throw new ObjectNotFoundException("Goods with id : "+id+" not found !!!");
        }
    }

}
