package ua.com.deluxedostavka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ua.com.deluxedostavka.entity.SubCategory;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory,Long>,JpaSpecificationExecutor<SubCategory>{

    List<SubCategory> findAllByCategoryId(Long id);

}
