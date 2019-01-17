package ua.com.deluxedostavka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import ua.com.deluxedostavka.dto.order.OrderRequest;
import ua.com.deluxedostavka.dto.order.OrderResponse;
import ua.com.deluxedostavka.dto.other.DataResponse;
import ua.com.deluxedostavka.dto.other.PageResponse;
import ua.com.deluxedostavka.dto.other.PaginationRequest;
import ua.com.deluxedostavka.exception.ObjectNotFoundException;
import ua.com.deluxedostavka.exception.OrderException;
import ua.com.deluxedostavka.service.OrderService;

import javax.validation.Valid;
import javax.xml.bind.JAXBException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping
    public List<OrderResponse> getAll(){
        return orderService.getAll();
    }

    @PostMapping("/archived/{id}")
    public OrderResponse archived(@PathVariable Long id){
        return orderService.archived(id);
    }

    @PostMapping("/unarchived/{id}")
    public OrderResponse unarchived(@PathVariable Long id){
        return orderService.unarchived(id);
    }

    @PostMapping("/page")
    public DataResponse<OrderResponse> getAll(@RequestBody PaginationRequest paginationRequest) {
        return orderService.getAll(paginationRequest);
    }

    @PostMapping("/page/archived")
    public DataResponse<OrderResponse> getAllArchived(@RequestBody PaginationRequest paginationRequest) {
        return orderService.getAllByArchived(paginationRequest, true);
    }

    @PostMapping("/page/unarchived")
    public DataResponse<OrderResponse> getAllUnarchived(@RequestBody PaginationRequest paginationRequest) {
        return orderService.getAllByArchived(paginationRequest, false);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        orderService.delete(id);
    }
    @PutMapping
    public void update(@RequestBody @Valid OrderRequest orderRequest) throws JAXBException, ObjectNotFoundException, OrderException {
        orderService.updateOrder(orderRequest);
    }

    @GetMapping("/search")
    public DataResponse<OrderResponse> searchOrder(String value, Integer page, Integer size,
                                                   @RequestParam(required = false) String sortBy,
                                                   @RequestParam(required = false) Sort.Direction direction){
        return orderService.searchOrders(value,page,size,sortBy,direction);
    }


}
