package ua.com.deluxedostavka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.deluxedostavka.dto.authentication.CredentialsRequest;
import ua.com.deluxedostavka.dto.authentication.TokenResponse;
import ua.com.deluxedostavka.dto.category.CategoryResponse;
import ua.com.deluxedostavka.dto.goods.GoodsResponse;
import ua.com.deluxedostavka.dto.order.OrderRequest;
import ua.com.deluxedostavka.dto.order.OrderResponse;
import ua.com.deluxedostavka.dto.other.DataResponse;
import ua.com.deluxedostavka.dto.other.PaginationRequest;
import ua.com.deluxedostavka.dto.subCategory.SubCategoryResponse;
import ua.com.deluxedostavka.exception.AuthenticationException;
import ua.com.deluxedostavka.exception.ObjectNotFoundException;
import ua.com.deluxedostavka.service.*;
import javax.validation.Valid;
import javax.xml.bind.JAXBException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;


    @PostMapping("/authentication")
    public TokenResponse authentication(@RequestBody @Valid CredentialsRequest credentialsRequest) throws AuthenticationException {
        return administratorService.authentication(credentialsRequest);
    }
    @GetMapping("/category")
    public List<CategoryResponse> getAllCategory(){
        return categoryService.getAll();
    }
    @GetMapping("/subCategory")
    public List<SubCategoryResponse> getAllSubCategory(){
        return subCategoryService.getAll();
    }
    @GetMapping("/subCategory/{idCategory}")
    public List<SubCategoryResponse> getAllSubCategoryByCategory(@PathVariable Long idCategory){
        return subCategoryService.getAllByCategory(idCategory);
    }
    @GetMapping("/goods")
    public List<GoodsResponse> getAllGoods(){
        return goodsService.getAll();
    }

    @PostMapping("/goods/page")
    public DataResponse<GoodsResponse> getPageGoods(@RequestBody PaginationRequest paginationRequest) {
        return goodsService.getPage(paginationRequest);
    }

    @PostMapping("/goods/page/by/category/{id}")
    public DataResponse<GoodsResponse> getPageGoodsByCategoryId(@PathVariable Long id, @RequestBody PaginationRequest paginationRequest) {
        return goodsService.getPageByCategoryId(id, paginationRequest);
    }

    @PostMapping("/goods/page/by/subcategory/{id}")
    public DataResponse<GoodsResponse> getPageGoodsBySubCategoryId(@PathVariable Long id, @RequestBody PaginationRequest paginationRequest) {
        return goodsService.getPageBySubCategoryId(id, paginationRequest);
    }

    @PostMapping("/goods/search")
    public DataResponse<GoodsResponse> getPageGoodsByName(@RequestParam String name, @RequestBody PaginationRequest paginationRequest) {
        return goodsService.findByName(name, paginationRequest);
    }

    @GetMapping("/goods/by/category/{idCategory}")
    public List<GoodsResponse> getAllGoodsByCategory(@PathVariable Long idCategory){
        return goodsService.getAllByCategory(idCategory);
    }
    @GetMapping("/goods/by/subCategory/{idSubCategory}")
    public List<GoodsResponse> getAllGoodsBySubCategory(@PathVariable Long idSubCategory){
        return goodsService.getAllBySubCategory(idSubCategory);
    }
    @PostMapping("/order")
    public OrderResponse order(@RequestBody @Valid OrderRequest orderRequest) throws ObjectNotFoundException, JAXBException {
        return orderService.order(orderRequest);
    }





}
