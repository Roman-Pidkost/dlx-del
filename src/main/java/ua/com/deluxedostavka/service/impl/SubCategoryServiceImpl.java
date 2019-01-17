package ua.com.deluxedostavka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.deluxedostavka.dto.subCategory.SubCategoryRequest;
import ua.com.deluxedostavka.dto.subCategory.SubCategoryResponse;
import ua.com.deluxedostavka.entity.Category;
import ua.com.deluxedostavka.entity.SubCategory;
import ua.com.deluxedostavka.exception.ObjectNotFoundException;
import ua.com.deluxedostavka.repository.CategoryRepository;
import ua.com.deluxedostavka.repository.SubCategoryRepository;
import ua.com.deluxedostavka.service.SubCategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    @Transactional
    public List<SubCategoryResponse> getAll() {
        return subCategoryRepository.findAll().stream().map(SubCategoryResponse::new).collect(Collectors.toList());
    }

    @Override
    public List<SubCategoryResponse> getAllByCategory(Long categoryId) {
        return subCategoryRepository.findAllByCategoryId(categoryId).stream().map(SubCategoryResponse::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SubCategoryResponse getOne(Long id) {
        return new SubCategoryResponse(subCategoryRepository.findOne(id));
    }

    @Override
    @Transactional
    public SubCategoryResponse create(SubCategoryRequest subCategoryRequest) throws ObjectNotFoundException {
        Category category = findCategory(subCategoryRequest.getCategoryId());
        SubCategory subCategory = new SubCategory();
        subCategory.setName(subCategoryRequest.getName());
        subCategory.setCategory(category);
        subCategory = subCategoryRepository.saveAndFlush(subCategory);
        return new SubCategoryResponse(subCategory);
    }

    @Override
    @Transactional
    public SubCategoryResponse update(Long id, SubCategoryRequest subCategoryRequest) throws ObjectNotFoundException {
        SubCategory subCategory = findSubCategory(id);
        Category category = findCategory(subCategoryRequest.getCategoryId());
        subCategory.setName(subCategoryRequest.getName());
        subCategory.setCategory(category);
        subCategory = subCategoryRepository.saveAndFlush(subCategory);
        return new SubCategoryResponse(subCategory);
    }

    @Override
    public void delete(Long id) {
        subCategoryRepository.delete(id);
    }

    private SubCategory findSubCategory(Long id) throws ObjectNotFoundException {
        SubCategory subCategory = subCategoryRepository.findOne(id);
        if (subCategory != null) {
            return subCategory;
        } else {
            throw new ObjectNotFoundException("SubCategory with id :" + id + " not found");
        }
    }

    private Category findCategory(Long id) throws ObjectNotFoundException {
        Category category = categoryRepository.findOne(id);
        if (category != null) {
            return category;
        } else {
            throw new ObjectNotFoundException("Category with id :" + id + " not found");
        }
    }


}
