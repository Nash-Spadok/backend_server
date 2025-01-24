package com.nash_spadok.backend_server.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequestDto {
    private Long productId;
    private Integer quantity;
}
