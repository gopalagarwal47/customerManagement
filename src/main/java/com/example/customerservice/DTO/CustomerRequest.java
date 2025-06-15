package com.example.customerservice.DTO;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequest {
    private String firstName;
    private String lastName;
    private String email;
    private MultipartFile image;
    private String phone;
    private String address;
    private String birthDate;
}
