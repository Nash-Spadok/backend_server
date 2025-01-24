package com.nash_spadok.backend_server.mapper;

import com.nash_spadok.backend_server.config.MapperConfig;
import com.nash_spadok.backend_server.dto.OrderRequestDto;
import com.nash_spadok.backend_server.dto.OrderResponseDto;
import com.nash_spadok.backend_server.model.order.Order;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface OrderMapper {
    Order toEntity(OrderRequestDto orderRequestDto);

    OrderResponseDto toDto(Order order);
}
