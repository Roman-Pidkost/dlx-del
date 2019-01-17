package ua.com.deluxedostavka.dto.subCategory;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class SubCategoryRequest {

    @NotNull
    private String name;

    @NotNull
    private Long categoryId;
}
