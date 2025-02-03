package com.nashspadok.backendserver.mapper;

import com.nashspadok.backendserver.config.MapperConfig;
import com.nashspadok.backendserver.dto.OrderRequestDto;
import com.nashspadok.backendserver.dto.OrderResponseDto;
import com.nashspadok.backendserver.model.order.Order;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface OrderMapper {
    Order toEntity(OrderRequestDto orderRequestDto);

    OrderResponseDto toDto(Order order);
}
