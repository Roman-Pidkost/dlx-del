package ua.com.deluxedostavka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.deluxedostavka.entity.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator,Long>{

    Administrator findByLogin(String login);
}
