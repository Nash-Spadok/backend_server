package com.nash_spadok.backend_server.repository;

import com.nash_spadok.backend_server.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
