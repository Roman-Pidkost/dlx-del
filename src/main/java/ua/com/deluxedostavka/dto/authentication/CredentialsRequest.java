package ua.com.deluxedostavka.dto.authentication;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CredentialsRequest {

    @NotNull
    @Size(min = 8,max = 16)
    private String login;

    @NotNull
    @Size(min = 8,max = 16)
    private String password;


}
