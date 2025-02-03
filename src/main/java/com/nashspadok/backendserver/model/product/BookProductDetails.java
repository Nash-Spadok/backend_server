package com.nashspadok.backendserver.model.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
