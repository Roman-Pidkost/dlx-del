package ua.com.deluxedostavka.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter @Setter
@Entity
public class SubCategory extends IdHolder{

    private String name;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "subCategory",cascade = CascadeType.REMOVE)
    private Set<Goods> goods = new LinkedHashSet<>();

    @Override
    public String toString() {
        return name;
    }
}
