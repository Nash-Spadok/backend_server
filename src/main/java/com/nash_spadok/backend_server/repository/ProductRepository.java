package com.nash_spadok.backend_server.repository;

import com.nash_spadok.backend_server.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
