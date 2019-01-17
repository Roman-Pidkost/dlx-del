package ua.com.deluxedostavka.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import ua.com.deluxedostavka.dto.goods.GoodsForOrderResponse;
import ua.com.deluxedostavka.dto.goods.GoodsResponse;
import ua.com.deluxedostavka.dto.user.UserResponse;
import ua.com.deluxedostavka.entity.GoodsForOrder;
import ua.com.deluxedostavka.entity.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@Log
public class OrderResponse {

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date;

    private Boolean archive;

    private String description;

    private Long sum;

    private UserResponse userResponse;

    private List<GoodsForOrderResponse> goodsResponseList = new ArrayList<>();


    public OrderResponse(Order order) {
        this.id = order.getId();
        this.date = order.getDate();
        this.archive = order.getArchive();
        this.sum = order.getSum();
        this.description = order.getDescription();
        this.userResponse = new UserResponse(order.getUser());
        goodsResponseList.addAll(order.getGoodsForOrders().stream().map(GoodsForOrderResponse::new).collect(Collectors.toList()));
    }
}
