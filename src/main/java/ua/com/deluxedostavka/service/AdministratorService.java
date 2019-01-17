package ua.com.deluxedostavka.service;


import ua.com.deluxedostavka.dto.authentication.CredentialsRequest;
import ua.com.deluxedostavka.dto.authentication.TokenResponse;
import ua.com.deluxedostavka.exception.AuthenticationException;

public interface AdministratorService {

    TokenResponse authentication(CredentialsRequest credentialsRequest) throws AuthenticationException;
}
