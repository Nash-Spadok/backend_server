package com.nash_spadok.backend_server.service.impl;

import com.nash_spadok.backend_server.dto.OrderItemRequestDto;
import com.nash_spadok.backend_server.exception.ProductNotAvailableException;
import com.nash_spadok.backend_server.mapper.OrderItemMapper;
import com.nash_spadok.backend_server.model.order.Order;
import com.nash_spadok.backend_server.model.order.OrderItem;
import com.nash_spadok.backend_server.model.product.Product;
import com.nash_spadok.backend_server.repository.product.ProductRepository;
import com.nash_spadok.backend_server.service.OrderItemService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemMapper orderItemMapper;
    private final ProductRepository productRepository;

    @Override
    public OrderItem createOrderItem(OrderItemRequestDto orderItemRequestDto, Order order) {
        OrderItem orderItem = orderItemMapper.toEntity(orderItemRequestDto);
        Product product = setProduct(orderItemRequestDto.getProductId());
        orderItem.setProduct(product);
        orderItem.setPrice(product.getPrice());
        changeProductStatus(product);
        orderItem.setOrder(order);
        return orderItem;
    }

    private Product setProduct(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can`t find product with id " + id)
        );
    }

    private void changeProductStatus(Product product) {
        if (!product.isAvailable()) {
            throw new ProductNotAvailableException("Product with id " + product.getId()
            + " is not available");
        }
        product.setAvailable(false);
        productRepository.save(product);
    }
}
