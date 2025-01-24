package com.nash_spadok.backend_server.repository;

import com.nash_spadok.backend_server.model.category.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long>, JpaSpecificationExecutor<SubCategory> {
    List<SubCategory> findAllByCategoryId(Long id);
}
