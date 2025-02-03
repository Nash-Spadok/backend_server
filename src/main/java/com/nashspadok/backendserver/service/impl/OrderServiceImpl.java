package com.nashspadok.backendserver.service.impl;

import com.nashspadok.backendserver.dto.OrderItemRequestDto;
import com.nashspadok.backendserver.dto.OrderRequestDto;
import com.nashspadok.backendserver.dto.OrderResponseDto;
import com.nashspadok.backendserver.mapper.OrderMapper;
import com.nashspadok.backendserver.model.order.Order;
import com.nashspadok.backendserver.model.order.OrderItem;
import com.nashspadok.backendserver.repository.OrderRepository;
import com.nashspadok.backendserver.service.OrderItemService;
import com.nashspadok.backendserver.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private List<OrderItem> setOrderItem(List<OrderItemRequestDto> orderItemRequestDto,
                                         Order order) {
        return orderItemRequestDto.stream()
                .map(item -> orderItemService.createOrderItem(item, order))
                .toList();

    }
}
