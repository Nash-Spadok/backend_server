package com.nashspadok.backendserver.service;

import com.nashspadok.backendserver.dto.OrderRequestDto;
import com.nashspadok.backendserver.dto.OrderResponseDto;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);
}
