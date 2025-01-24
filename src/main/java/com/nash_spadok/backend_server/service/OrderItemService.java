package com.nash_spadok.backend_server.service;

import com.nash_spadok.backend_server.dto.OrderItemRequestDto;
import com.nash_spadok.backend_server.model.order.Order;
import com.nash_spadok.backend_server.model.order.OrderItem;
import com.nash_spadok.backend_server.model.product.Product;

public interface OrderItemService {
    OrderItem createOrderItem(OrderItemRequestDto orderItemRequestDto, Order order);
}
