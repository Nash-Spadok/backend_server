package com.nashspadok.backendserver.repository;

import com.nashspadok.backendserver.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
