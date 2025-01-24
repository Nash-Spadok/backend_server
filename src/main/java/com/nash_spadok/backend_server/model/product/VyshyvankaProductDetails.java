package com.nash_spadok.backend_server.model.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "vyshyvanka_product_details")
@Getter
@Setter
public class VyshyvankaProductDetails extends Product{
    @Column(nullable = false)
    private String size;

    @ElementCollection
    @CollectionTable(
            name = "vyshyvanka_product_details_sizes",
            joinColumns = @JoinColumn(name = "vyshyvanka_product_details_id")
    )
    @Column(name = "size")
    private List<String> sizesAvailable;
}
