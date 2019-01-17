package ua.com.deluxedostavka.dto.authentication;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TokenResponse {

    @NotNull
    private String token;

    public TokenResponse(String token) {
        this.token = token;
    }
}
