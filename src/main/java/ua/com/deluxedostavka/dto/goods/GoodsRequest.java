package ua.com.deluxedostavka.dto.goods;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GoodsRequest {

    private String name;

    private Long price;

    private Long weight;

    private String description;

    private String img;

    private Long  subCategoryId;


}
