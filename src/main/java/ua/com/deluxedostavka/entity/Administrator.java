package ua.com.deluxedostavka.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class Administrator extends IdHolder{

    @Size(max = 16,min = 8)
    private String login;

    @Size(max = 16,min = 8)
    private String password;

    private Role role;

}
