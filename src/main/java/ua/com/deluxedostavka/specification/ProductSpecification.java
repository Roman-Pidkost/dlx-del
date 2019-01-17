package ua.com.deluxedostavka.specification;


import org.springframework.data.jpa.domain.Specification;
import ua.com.deluxedostavka.entity.Goods;
import ua.com.deluxedostavka.entity.SubCategory;

import javax.persistence.criteria.*;

public class ProductSpecification implements Specification<Goods>{

    private String text;

    private Boolean less;

    private Long price;

    public ProductSpecification(String text, Boolean less, Long price) {
        this.text = text;
        this.less = less;
        this.price = price;
    }

    private Predicate searchByNameAndSubCategory(Root<Goods> root, CriteriaBuilder cb){
        if(text == null || text.equals("")){
            return cb.conjunction();
        }
        Join<Goods,SubCategory> joinGoodsToSubCategory = root.join("subCategory");
        Predicate searchBySubCategory = cb.like(joinGoodsToSubCategory.get("name"),"%"+text+"%");
        Predicate searchByName = cb.like(root.get("name"),"%"+text+"%");
        return cb.or(searchBySubCategory,searchByName);
    }

    private Predicate searchByPrice(Root<Goods> root, CriteriaBuilder cb){
        if(price == null || less == null){
            return cb.conjunction();
        }
        Predicate filterByPrice = null;
        if(less){
            filterByPrice = cb.lessThanOrEqualTo(root.get("price"),price);
        }else{
            filterByPrice = cb.greaterThanOrEqualTo(root.get("price"),price);
        }
        return filterByPrice;
    }


    @Override
    public Predicate toPredicate(Root<Goods> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return cb.and(searchByNameAndSubCategory(root,cb),searchByPrice(root,cb));
    }
}
