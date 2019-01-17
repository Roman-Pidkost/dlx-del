package ua.com.deluxedostavka.dto.category;

import lombok.Getter;
import lombok.Setter;
import ua.com.deluxedostavka.entity.Category;

@Getter @Setter
public class CategoryResponse {

    private Long id;

    private String name;

    private int subcategoriesCount;

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.subcategoriesCount = category.getSubCategoryList().size();
    }

    @Override
    public String toString() {
        return name;
    }
}
