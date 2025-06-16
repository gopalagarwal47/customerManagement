package com.example.customerservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderModal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private Integer quantity;
    private Double price;
    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private CustomerModal customer;
}
