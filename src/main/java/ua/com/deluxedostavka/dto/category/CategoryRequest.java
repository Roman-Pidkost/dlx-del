package ua.com.deluxedostavka.dto.category;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter @Getter
public class CategoryRequest {

    @NotNull
    private String name;
}
