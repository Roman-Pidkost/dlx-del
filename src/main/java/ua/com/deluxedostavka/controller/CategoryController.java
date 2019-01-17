package ua.com.deluxedostavka.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.deluxedostavka.dto.category.CategoryRequest;
import ua.com.deluxedostavka.dto.category.CategoryResponse;
import ua.com.deluxedostavka.exception.ObjectNotFoundException;
import ua.com.deluxedostavka.service.CategoryService;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public CategoryResponse create(@RequestBody @Valid CategoryRequest categoryRequest){
        return categoryService.create(categoryRequest);
    }

    @PutMapping("/{id}")
    public CategoryResponse update(@PathVariable Long id, @RequestBody @Valid CategoryRequest categoryRequest) throws ObjectNotFoundException {
        return categoryService.update(id,categoryRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        categoryService.delete(id);
    }

    @GetMapping
    public CategoryResponse getById(@RequestParam Long id) {
        return this.categoryService.getOne(id);
    }
}
