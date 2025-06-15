package com.example.customerservice.service;

import com.example.customerservice.model.CustomerModal;

import java.util.List;

public interface Serviceinterface {
    CustomerModal findById(long id);
    List<CustomerModal> getAllCustomers();
    CustomerModal create(CustomerModal customer);
    CustomerModal update(long id ,CustomerModal customer);
    void delete(long id);

}