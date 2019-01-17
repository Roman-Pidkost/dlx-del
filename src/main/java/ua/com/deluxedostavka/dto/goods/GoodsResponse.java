package ua.com.deluxedostavka.dto.goods;

import lombok.Getter;
import lombok.Setter;
import ua.com.deluxedostavka.dto.subCategory.SubCategoryResponse;
import ua.com.deluxedostavka.entity.Goods;

import java.awt.font.TextHitInfo;

@Getter @Setter
public class GoodsResponse {

    private Long id;

    private String name;

    private Long price;

    private Long weight;

    private String description;

    private String pathImage;

    private SubCategoryResponse subCategoryResponse;

    public GoodsResponse(Goods goods) {
        this.id = goods.getId();
        this.name = goods.getName();
        this.price = goods.getPrice();
        this.weight = goods.getWeight();
        this.description = goods.getDescription();
        this.pathImage = goods.getPathImage();
        this.subCategoryResponse = new SubCategoryResponse(goods.getSubCategory());
    }
}
