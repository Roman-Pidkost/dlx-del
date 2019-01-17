package ua.com.deluxedostavka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.deluxedostavka.dto.category.CategoryRequest;
import ua.com.deluxedostavka.dto.category.CategoryResponse;
import ua.com.deluxedostavka.entity.Category;
import ua.com.deluxedostavka.exception.ObjectNotFoundException;
import ua.com.deluxedostavka.repository.CategoryRepository;
import ua.com.deluxedostavka.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<CategoryResponse> getAll() {
        return categoryRepository.findAll().stream().map(CategoryResponse::new).collect(Collectors.toList());
    }

    @Override
    public CategoryResponse getOne(Long id) {
        return new CategoryResponse(categoryRepository.findOne(id));
    }

    @Override
    @Transactional
    public CategoryResponse create(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category = categoryRepository.saveAndFlush(category);
        return new CategoryResponse(category);
    }

    @Override
    public CategoryResponse update(Long id, CategoryRequest categoryRequest) throws ObjectNotFoundException {
        Category category = findCategory(id);
        category.setName(categoryRequest.getName());
        category = categoryRepository.saveAndFlush(category);
        return new CategoryResponse(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.delete(id);
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
