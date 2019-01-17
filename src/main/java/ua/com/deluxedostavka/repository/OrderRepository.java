package ua.com.deluxedostavka.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.deluxedostavka.entity.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{

    Page<Order> findByArchive(Boolean archive, Pageable pageable);

    @Query("select o from Order o join o.user u where u.name like :valueSearch or u.surName like :valueSearch or u.number like :valueSearch")
    Page<Order> findByUserNameOrUserSurNameOrUserNumber(@Param("valueSearch") String valueSearch, Pageable pageable);
}
