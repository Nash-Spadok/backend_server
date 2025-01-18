package com.nash_spadok.backend_server.repository.category;

import com.nash_spadok.backend_server.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
