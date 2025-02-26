package com.nashspadok.backendserver.controller;

import com.nashspadok.backendserver.dto.OrderRequestDto;
import com.nashspadok.backendserver.dto.OrderResponseDto;
import com.nashspadok.backendserver.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
@Validated
@Tag(name = "Order", description = "Order API")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "Create order")
    public OrderResponseDto createOrder(@RequestBody @Valid OrderRequestDto orderRequestDto) {
        return orderService.createOrder(orderRequestDto);
    }
}
