package com.nash_spadok.backend_server.service.impl;

import com.nash_spadok.backend_server.dto.OrderItemRequestDto;
import com.nash_spadok.backend_server.dto.OrderItemResponseDto;
import com.nash_spadok.backend_server.dto.OrderRequestDto;
import com.nash_spadok.backend_server.dto.OrderResponseDto;
import com.nash_spadok.backend_server.exception.EntityNotFoundException;
import com.nash_spadok.backend_server.mapper.OrderMapper;
import com.nash_spadok.backend_server.model.order.Order;
import com.nash_spadok.backend_server.model.order.OrderItem;
import com.nash_spadok.backend_server.repository.OrderRepository;
import com.nash_spadok.backend_server.repository.product.ProductRepository;
import com.nash_spadok.backend_server.service.OrderItemService;
import com.nash_spadok.backend_server.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;

    @Override
    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        Order order = orderMapper.toEntity(orderRequestDto);
        List<OrderItem> orderItems = setOrderItem(orderRequestDto.getOrderItems(), order);
        order.setOrderItems(orderItems);
        return orderMapper.toDto(orderRepository.save(order));
    }

    private List<OrderItem> setOrderItem(List<OrderItemRequestDto> orderItemRequestDto, Order order) {
        return orderItemRequestDto.stream()
                .map(item -> orderItemService.createOrderItem(item, order))
                .toList();

    }
}
