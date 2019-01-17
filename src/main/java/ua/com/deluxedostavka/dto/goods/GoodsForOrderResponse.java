package ua.com.deluxedostavka.dto.goods;

import lombok.Getter;
import lombok.Setter;
import ua.com.deluxedostavka.dto.subCategory.SubCategoryResponse;
import ua.com.deluxedostavka.entity.Goods;
import ua.com.deluxedostavka.entity.GoodsForOrder;

@Getter @Setter
public class GoodsForOrderResponse {
    private Long id;

    private String name;

    private Long price;

    private Long weight;

    private String description;

    private String pathImage;

    private Long count;

    private SubCategoryResponse subCategoryResponse;

    public GoodsForOrderResponse(GoodsForOrder goods) {
        this.id = goods.getId();
        this.name = goods.getGoods().getName();
        this.price = goods.getGoods().getPrice();
        this.weight = goods.getGoods().getWeight();
        this.description = goods.getGoods().getDescription();
        this.pathImage = goods.getGoods().getPathImage();
        this.count = goods.getCount();
        this.subCategoryResponse = new SubCategoryResponse(goods.getGoods().getSubCategory());
    }


}
