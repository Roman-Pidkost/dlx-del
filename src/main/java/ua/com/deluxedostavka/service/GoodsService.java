package ua.com.deluxedostavka.service;

import ua.com.deluxedostavka.dto.goods.GoodsRequest;
import ua.com.deluxedostavka.dto.goods.GoodsResponse;
import ua.com.deluxedostavka.dto.other.DataResponse;
import ua.com.deluxedostavka.dto.other.PaginationRequest;
import ua.com.deluxedostavka.exception.ObjectNotFoundException;

import java.io.IOException;
import java.util.List;

public interface GoodsService {

    List<GoodsResponse> getAll();

    DataResponse<GoodsResponse> getPage(PaginationRequest paginationRequest);

    DataResponse<GoodsResponse> getPageByCategoryId(Long id, PaginationRequest paginationRequest);

    DataResponse<GoodsResponse> getPageBySubCategoryId(Long id, PaginationRequest paginationRequest);

    List<GoodsResponse> getAllBySubCategory(Long idSubCategory);

    List<GoodsResponse> getAllByCategory(Long idCategory);

    GoodsResponse getOne(Long idGoods);

    DataResponse<GoodsResponse> findByName(String name, PaginationRequest paginationRequest);

    GoodsResponse create(GoodsRequest goodsRequest) throws IOException, ObjectNotFoundException;

    GoodsResponse update(Long id ,GoodsRequest goodsRequest) throws ObjectNotFoundException, IOException;

    void delete(Long id) throws ObjectNotFoundException;


}
