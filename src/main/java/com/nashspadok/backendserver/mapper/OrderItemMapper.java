package com.nashspadok.backendserver.mapper;

import com.nashspadok.backendserver.config.MapperConfig;
import com.nashspadok.backendserver.dto.OrderItemRequestDto;
import com.nashspadok.backendserver.model.order.OrderItem;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {
    OrderItem toEntity(OrderItemRequestDto orderItemRequestDto);
}
