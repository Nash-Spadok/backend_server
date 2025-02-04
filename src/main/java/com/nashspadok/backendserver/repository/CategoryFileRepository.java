package com.nashspadok.backendserver.repository;

import com.nashspadok.backendserver.model.file.CategoryFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryFileRepository extends JpaRepository<CategoryFile, Long> {
}
