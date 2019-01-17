package ua.com.deluxedostavka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.deluxedostavka.dto.goods.GoodsRequest;
import ua.com.deluxedostavka.dto.goods.GoodsResponse;
import ua.com.deluxedostavka.exception.ObjectNotFoundException;
import ua.com.deluxedostavka.service.GoodsService;

import javax.validation.Valid;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping
    public GoodsResponse create(@RequestBody @Valid GoodsRequest goodsRequest) throws IOException, ObjectNotFoundException {
        return goodsService.create(goodsRequest);
    }

    @PutMapping("/{id}")
    public GoodsResponse update(@PathVariable Long id, @RequestBody @Valid GoodsRequest goodsRequest) throws IOException, ObjectNotFoundException {
        return goodsService.update(id, goodsRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws ObjectNotFoundException {
        goodsService.delete(id);
    }
}
