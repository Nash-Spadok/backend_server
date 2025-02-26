package com.nashspadok.backendserver.service;

import com.nashspadok.backendserver.dto.OrderItemRequestDto;
import com.nashspadok.backendserver.model.order.Order;
import com.nashspadok.backendserver.model.order.OrderItem;

public interface OrderItemService {
    OrderItem createOrderItem(OrderItemRequestDto orderItemRequestDto, Order order);
}
