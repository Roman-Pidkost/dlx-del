package ua.com.deluxedostavka.dto.user;

import lombok.Getter;
import lombok.Setter;
import ua.com.deluxedostavka.entity.User;

@Getter @Setter
public class UserResponse {

    private String name;

    private String surName;

    private String number;

    private String address;

    private String addressNumber;

    public UserResponse(User user) {
        this.name = user.getName();
        this.surName = user.getSurName();
        this.number = user.getNumber();
        this.address = user.getAddress();
        this.addressNumber = user.getAddressNumber();
    }
}
