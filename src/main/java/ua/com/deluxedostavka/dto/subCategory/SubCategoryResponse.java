package ua.com.deluxedostavka.dto.subCategory;

import lombok.Getter;
import lombok.Setter;
import ua.com.deluxedostavka.dto.category.CategoryResponse;
import ua.com.deluxedostavka.entity.SubCategory;

@Getter @Setter
public class SubCategoryResponse {

    private Long id;

    private String name;

    private CategoryResponse categoryResponse;

    public SubCategoryResponse(SubCategory subCategory){
        this.id = subCategory.getId();
        this.name = subCategory.getName();
        this.categoryResponse = new CategoryResponse(subCategory.getCategory());

    }

    @Override
    public String toString() {
        return name;
    }
}
