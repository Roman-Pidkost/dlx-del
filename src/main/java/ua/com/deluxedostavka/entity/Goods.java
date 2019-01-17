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
public class Goods extends IdHolder{

    private String name;

    private Long price;

    private Long weight;

    private String description;

    private String pathImage;

    @ManyToOne
    private  SubCategory subCategory;

    @OneToMany(mappedBy = "goods",cascade = CascadeType.REMOVE)
    private Set<GoodsForOrder> goodsForOrders = new LinkedHashSet<>();




}
