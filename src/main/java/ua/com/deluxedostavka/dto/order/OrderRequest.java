package ua.com.deluxedostavka.dto.order;

import lombok.Getter;
import lombok.Setter;
import ua.com.deluxedostavka.dto.goods.GoodsForOrderRequest;
import ua.com.deluxedostavka.dto.user.UserRequest;
import java.util.List;

@Getter @Setter
public class OrderRequest {

    private Long id;

    private Boolean archive;

    private String description;

    private Long sum;

    private UserRequest user;

    private List<GoodsForOrderRequest> goods;

}
