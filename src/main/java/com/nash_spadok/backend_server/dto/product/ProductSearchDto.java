package com.nash_spadok.backend_server.dto.product;

import java.util.Arrays;
import java.util.Objects;

public record ProductSearchDto(String[] prices, String[] categories) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductSearchDto that = (ProductSearchDto) o;
        return Objects.deepEquals(prices, that.prices) && Objects.deepEquals(categories, that.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(prices), Arrays.hashCode(categories));
    }

    @Override
    public String toString() {
        return "ProductSearchDto{" +
                "prices=" + Arrays.toString(prices) +
                ", categories=" + Arrays.toString(categories) +
                '}';
    }
}
