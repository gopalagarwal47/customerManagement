package com.example.customerservice.model;


import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;


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

}

