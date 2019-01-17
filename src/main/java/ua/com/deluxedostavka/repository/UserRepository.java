package ua.com.deluxedostavka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.deluxedostavka.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
