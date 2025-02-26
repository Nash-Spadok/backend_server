package com.nashspadok.backendserver.repository.product;

import com.nashspadok.backendserver.model.product.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {
    List<Product> findBySubCategoryIdOrderByTitleAsc(Long id);

    @Query("SELECT p FROM Product p "
            + "JOIN p.subCategory s "
            + "JOIN s.category c "
            + "WHERE c.id = :categoryId")
    List<Product> findAllByCategoryId(Long id);
}
