package com.nashspadok.backendserver.service.impl;

import com.nashspadok.backendserver.dto.OrderItemRequestDto;
import com.nashspadok.backendserver.exception.ProductNotAvailableException;
import com.nashspadok.backendserver.mapper.OrderItemMapper;
import com.nashspadok.backendserver.model.order.Order;
import com.nashspadok.backendserver.model.order.OrderItem;
import com.nashspadok.backendserver.model.product.Product;
import com.nashspadok.backendserver.repository.product.ProductRepository;
import com.nashspadok.backendserver.service.OrderItemService;
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
