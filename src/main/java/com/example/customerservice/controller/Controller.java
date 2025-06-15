package com.example.customerservice.controller;


import com.example.customerservice.model.CustomerModal;
import com.example.customerservice.service.CustomerService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;



@RestController
@RequestMapping("/api/customer")
@CrossOrigin("*")
public class Controller {


    private final CustomerService customerService;

    public Controller(CustomerService customerService) {
        this.customerService = customerService;
    }




    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CustomerModal> createCustomer(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String address,
            @RequestParam String birthDate, // format: yyyy-MM-dd
            @RequestParam MultipartFile image
    ) {
        try {
            if (image == null || image.isEmpty()) {
                return ResponseEntity.badRequest().body(null);
            }

            // Use a folder outside /resources/ to avoid packaging issues
            Path uploadPath = Paths.get("uploads/");
            Files.createDirectories(uploadPath);

            String filename = UUID.randomUUID() + "_" + image.getOriginalFilename();
            Path filePath = uploadPath.resolve(filename);
            Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            CustomerModal customer = CustomerModal.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .phone(phone)
                    .address(address)
                    .birthDate(LocalDate.parse(birthDate))
                    .image("uploads/" + filename)
                    .build();

            return ResponseEntity.ok(customerService.create(customer));
        } catch (Exception e) {
            e.printStackTrace(); // ⬅️ Helps you see the root cause
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CustomerModal>> getAll() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerModal> getById(@PathVariable Long id) {
        CustomerModal customer = customerService.findById(id);
        return  ResponseEntity.ok(customer);
    }


}
