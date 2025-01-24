package com.nash_spadok.backend_server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequestDto {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Phone is mandatory")
    private String phone;

    @NotNull(message = "ProductIds is mandatory")
    private List<OrderItemRequestDto> orderItems;
}
