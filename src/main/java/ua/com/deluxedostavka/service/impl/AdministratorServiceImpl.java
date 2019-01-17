package ua.com.deluxedostavka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.deluxedostavka.dto.authentication.CredentialsRequest;
import ua.com.deluxedostavka.dto.authentication.TokenResponse;
import ua.com.deluxedostavka.entity.Administrator;
import ua.com.deluxedostavka.exception.AuthenticationException;
import ua.com.deluxedostavka.security.tokenUtils.TokenTools;
import ua.com.deluxedostavka.service.AdministratorService;
import ua.com.deluxedostavka.validator.AdministratorValidator;

@Service
public class AdministratorServiceImpl implements AdministratorService {


    @Autowired
    private AdministratorValidator administratorValidator;

    @Autowired
    private TokenTools tokenTools;

    @Override
    public TokenResponse authentication(CredentialsRequest credentialsRequest) throws AuthenticationException {
        Administrator administrator = administratorValidator.validateAuthenticated(credentialsRequest);
        String token = tokenTools.tokenGenerator(administrator.getLogin(),administrator.getRole().name());
        return new TokenResponse(token);
    }
}
