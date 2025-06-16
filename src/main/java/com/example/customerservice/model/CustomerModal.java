package com.example.customerservice.model;



import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

import com.example.customerservice.model.OrderModal;


@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

        public class CustomerModal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
            private String firstName;
    private String lastName;
    private String email;
    private String image;
    private String phone;
    private String address;
    private LocalDate birthDate;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderModal> orders;

}

