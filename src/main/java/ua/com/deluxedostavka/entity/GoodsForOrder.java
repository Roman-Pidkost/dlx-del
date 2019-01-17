package ua.com.deluxedostavka.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter @Setter
@Entity
public class GoodsForOrder extends IdHolder{

    private Long count;

    @ManyToOne
    private Goods goods;

    @ManyToOne
    private Order order;

}
