package ua.com.deluxedostavka.specification;

import org.springframework.data.jpa.domain.Specification;
import ua.com.deluxedostavka.entity.Category;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class CategorySpecification implements Specification<Category> {

    private String text;

    public CategorySpecification(String text) {
        this.text = text;
    }
    private Predicate searchByName(Root<Category> root, CriteriaBuilder cb){
        if(text == null || text.equals("")){
            return cb.conjunction();
        }
        return cb.like(root.get("name"),"%"+text +"%");
    }

    @Override
    public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return searchByName(root,cb);
    }
}
