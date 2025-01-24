package com.nash_spadok.backend_server.mapper;

import com.nash_spadok.backend_server.config.MapperConfig;
import com.nash_spadok.backend_server.dto.OrderItemRequestDto;
import com.nash_spadok.backend_server.model.order.OrderItem;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {
    OrderItem toEntity(OrderItemRequestDto orderItemRequestDto);
}
