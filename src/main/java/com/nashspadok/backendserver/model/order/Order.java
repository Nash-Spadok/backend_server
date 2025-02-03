package com.nashspadok.backendserver.model.order;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "order",
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH}
    )
    private List<OrderItem> orderItems = new ArrayList<>();

    @PrePersist
    private void onCreate() {
        this.orderDate = LocalDateTime.now();
    }
}
