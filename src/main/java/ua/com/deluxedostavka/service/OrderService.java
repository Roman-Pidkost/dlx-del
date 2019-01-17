package ua.com.deluxedostavka.service;


import org.springframework.data.domain.Sort;
import ua.com.deluxedostavka.dto.order.OrderRequest;
import ua.com.deluxedostavka.dto.order.OrderResponse;
import ua.com.deluxedostavka.dto.other.DataResponse;
import ua.com.deluxedostavka.dto.other.PageResponse;
import ua.com.deluxedostavka.dto.other.PaginationRequest;
import ua.com.deluxedostavka.exception.ObjectNotFoundException;
import ua.com.deluxedostavka.exception.OrderException;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface OrderService {

    OrderResponse order(OrderRequest orderRequest) throws ObjectNotFoundException, JAXBException;

    OrderResponse updateOrder(OrderRequest orderRequest) throws ObjectNotFoundException, JAXBException, OrderException;

    List<OrderResponse> getAll();

    DataResponse<OrderResponse> getAll(PaginationRequest paginationRequest);

    DataResponse<OrderResponse> getAllByArchived(PaginationRequest paginationRequest, Boolean archived);

    OrderResponse archived(Long id);

    OrderResponse unarchived(Long id);

    void delete(Long id);

    DataResponse<OrderResponse> searchOrders(String value, Integer page, Integer size, String sortBy, Sort.Direction direction);
}
