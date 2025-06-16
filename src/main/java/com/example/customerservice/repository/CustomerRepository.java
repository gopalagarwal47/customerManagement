package com.example.customerservice.repository;

import com.example.customerservice.model.CustomerModal;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModal, Long> {


    @Query("SELECT c FROM CustomerModal c")
    List<CustomerModal> findAllCustomers();





}
