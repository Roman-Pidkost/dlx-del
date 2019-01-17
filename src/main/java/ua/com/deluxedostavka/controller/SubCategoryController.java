package ua.com.deluxedostavka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.deluxedostavka.dto.subCategory.SubCategoryRequest;
import ua.com.deluxedostavka.dto.subCategory.SubCategoryResponse;
import ua.com.deluxedostavka.exception.ObjectNotFoundException;
import ua.com.deluxedostavka.service.SubCategoryService;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/subCategory")
public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;

    @PostMapping
    public SubCategoryResponse create(@RequestBody @Valid SubCategoryRequest subCategoryRequest) throws ObjectNotFoundException {
        return subCategoryService.create(subCategoryRequest);
    }

    @PutMapping("/{idCategory}")
    public SubCategoryResponse update(@PathVariable Long idCategory, @RequestBody @Valid SubCategoryRequest subCategoryRequest) throws ObjectNotFoundException {
        return subCategoryService.update(idCategory,subCategoryRequest);
    }

    @DeleteMapping("{idSubCategory}")
    public void delete(@PathVariable Long idSubCategory){
        subCategoryService.delete(idSubCategory);
    }

    @GetMapping
    public SubCategoryResponse getById(@RequestParam Long id) {
        return this.subCategoryService.getOne(id);
    }
}
