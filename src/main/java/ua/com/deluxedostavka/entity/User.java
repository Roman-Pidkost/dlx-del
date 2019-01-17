package ua.com.deluxedostavka.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter @Setter
@Entity
@Table(name = "_user")
public class User extends IdHolder {

    private String name;

    private String surName;

    private String number;

    private String address;

    private String addressNumber;

    @OneToOne
    @MapsId
    private Order order;



}
