package com.nashspadok.backendserver.model.product;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vyshyvanka_product_details")
@Getter
@Setter
public class VyshyvankaProductDetails extends Product {
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
