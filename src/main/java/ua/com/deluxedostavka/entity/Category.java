package ua.com.deluxedostavka.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter @Setter
@Entity
public class Category extends IdHolder{

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "category",cascade = CascadeType.REMOVE)
    private Set<SubCategory> subCategoryList = new LinkedHashSet<>();

    @Override
    public String toString() {
        return name;
    }
}
