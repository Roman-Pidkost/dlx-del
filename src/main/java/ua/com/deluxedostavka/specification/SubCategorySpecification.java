package ua.com.deluxedostavka.specification;

import org.springframework.data.jpa.domain.Specification;
import ua.com.deluxedostavka.entity.SubCategory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class SubCategorySpecification implements Specification<SubCategory> {

    private String text;

    public SubCategorySpecification(String text) {
        this.text = text;
    }

    private Predicate searchByName(Root<SubCategory> root, CriteriaBuilder cb){
        if(text == null || text.equals("")){
            return cb.conjunction();
        }
        return cb.like(root.get("name"),"%"+text +"%");
    }

    @Override
    public Predicate toPredicate(Root<SubCategory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return searchByName(root,cb);
    }
}
