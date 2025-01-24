package com.nash_spadok.backend_server.model.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
