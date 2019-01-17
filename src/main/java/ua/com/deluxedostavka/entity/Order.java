package ua.com.deluxedostavka.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter @Setter
@Entity
@Table(name = "_order")
public class Order extends IdHolder{

    private LocalDateTime date;

    private Boolean archive;

    private String description;

    private Long sum;

    @OneToOne(cascade = CascadeType.REMOVE)
    @PrimaryKeyJoinColumn
    private User user;

    @OneToMany(mappedBy = "order",cascade = CascadeType.REMOVE)
    private Set<GoodsForOrder> goodsForOrders = new LinkedHashSet<>();

}
