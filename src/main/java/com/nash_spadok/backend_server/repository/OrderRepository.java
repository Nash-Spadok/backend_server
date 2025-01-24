package com.nash_spadok.backend_server.repository;

import com.nash_spadok.backend_server.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
