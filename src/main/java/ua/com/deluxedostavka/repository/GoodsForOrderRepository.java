package ua.com.deluxedostavka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.deluxedostavka.entity.GoodsForOrder;

import java.util.List;
import java.util.Set;

@Repository
public interface GoodsForOrderRepository extends JpaRepository<GoodsForOrder,Long> {

    Set<GoodsForOrder> findByOrderId(Long id);
}
