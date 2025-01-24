package com.nash_spadok.backend_server.model.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "book_product_details")
public class BookProductDetails extends Product {
    @Column(nullable = false)
    private String genre;
}
