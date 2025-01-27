package com.nash_spadok.backend_server.repository.product;

import com.nash_spadok.backend_server.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product> findBySubCategoryIdOrderByTitleAsc(Long id);

    @Query("SELECT p FROM Product p " +
            "JOIN p.subCategory s " +
            "JOIN s.category c " +
            "WHERE c.id = :categoryId")
    List<Product> findAllByCategoryId(Long id);
}
