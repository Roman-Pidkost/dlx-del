package ua.com.deluxedostavka.service;

import ua.com.deluxedostavka.dto.category.CategoryRequest;
import ua.com.deluxedostavka.dto.category.CategoryResponse;
import ua.com.deluxedostavka.entity.Category;
import ua.com.deluxedostavka.exception.ObjectNotFoundException;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> getAll();

    CategoryResponse getOne(Long id);

    CategoryResponse create(CategoryRequest categoryRequest);

    CategoryResponse update(Long id,CategoryRequest categoryRequest) throws ObjectNotFoundException;

    void delete(Long id);

}
