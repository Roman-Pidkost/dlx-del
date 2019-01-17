package ua.com.deluxedostavka.service;


import ua.com.deluxedostavka.dto.subCategory.SubCategoryRequest;
import ua.com.deluxedostavka.dto.subCategory.SubCategoryResponse;
import ua.com.deluxedostavka.exception.ObjectNotFoundException;

import java.util.List;

public interface SubCategoryService {

    List<SubCategoryResponse> getAll();

    List<SubCategoryResponse> getAllByCategory(Long categoryId);

    SubCategoryResponse getOne(Long id);

    SubCategoryResponse create(SubCategoryRequest subCategoryRequest) throws ObjectNotFoundException;

    SubCategoryResponse update(Long id ,SubCategoryRequest subCategoryRequest) throws ObjectNotFoundException;

    void delete(Long id);

}
