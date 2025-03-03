package com.nashspadok.backendserver.repository;

import com.nashspadok.backendserver.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
