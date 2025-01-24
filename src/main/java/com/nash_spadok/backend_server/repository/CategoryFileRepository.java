package com.nash_spadok.backend_server.repository;

import com.nash_spadok.backend_server.model.file.CategoryFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryFileRepository extends JpaRepository<CategoryFile, Long> {
}
