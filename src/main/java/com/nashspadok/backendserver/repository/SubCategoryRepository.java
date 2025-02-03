package com.nashspadok.backendserver.repository;

import com.nashspadok.backendserver.model.category.SubCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long>,
        JpaSpecificationExecutor<SubCategory> {
    List<SubCategory> findAllByCategoryId(Long id);
}
