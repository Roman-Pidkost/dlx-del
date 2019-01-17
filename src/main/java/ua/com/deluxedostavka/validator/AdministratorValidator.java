package ua.com.deluxedostavka.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.deluxedostavka.dto.authentication.CredentialsRequest;
import ua.com.deluxedostavka.entity.Administrator;
import ua.com.deluxedostavka.exception.AuthenticationException;
import ua.com.deluxedostavka.repository.AdministratorRepository;

@Component
public class AdministratorValidator {

    @Autowired
    private AdministratorRepository administratorRepository;

    public Administrator validateAuthenticated(CredentialsRequest credentialsRequest) throws AuthenticationException {
        Administrator administrator = administratorRepository.findByLogin(credentialsRequest.getLogin());
        if(administrator != null && administrator.getPassword().equals(credentialsRequest.getPassword())){
            return administrator;
        }else{
            throw new AuthenticationException("Wrong login or password");
        }
    }
}
