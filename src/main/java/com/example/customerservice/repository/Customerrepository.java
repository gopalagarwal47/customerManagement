package com.example.customerservice.repository;


import com.example.customerservice.model.CustomerModal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Customerrepository extends JpaRepository<CustomerModal, Long>{ }