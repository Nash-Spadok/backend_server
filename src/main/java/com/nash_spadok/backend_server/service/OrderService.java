package com.nash_spadok.backend_server.service;

import com.nash_spadok.backend_server.dto.OrderRequestDto;
import com.nash_spadok.backend_server.dto.OrderResponseDto;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);
}
