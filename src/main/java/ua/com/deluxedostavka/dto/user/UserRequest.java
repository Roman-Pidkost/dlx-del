package ua.com.deluxedostavka.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class UserRequest {

    @NotNull
    private String name;

    @NotNull
    private  String surName;

    @NotNull
    private String number;

    @NotNull
    private String address;

    @NotNull
    private String addressNumber;

}
