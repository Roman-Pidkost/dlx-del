package ua.com.deluxedostavka.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.deluxedostavka.entity.Goods;

import java.util.List;

@Repository
public interface GoodsRepository extends JpaRepository<Goods,Long>,JpaSpecificationExecutor<Goods>{

    List<Goods> findAllBySubCategoryId(Long id);

    @Query("select g from Goods g join g.subCategory s join s.category c where c.id=:idCategory")
    List<Goods> findAllByCategory(@Param("idCategory") Long idCategory);

    Page<Goods> findAllByNameLike(String name, Pageable pageable);

    Page<Goods> findAllBySubCategoryId(Long id, Pageable pageable);

    @Query("select g from Goods g join g.subCategory s join s.category c where c.id=:idCategory")
    Page<Goods> findAllByCategoryId(@Param("idCategory") Long idCategory, Pageable pageable);

}
