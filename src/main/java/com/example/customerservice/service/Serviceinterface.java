package com.example.customerservice.service;

import com.example.customerservice.model.CustomerModal;

public interface Serviceinterface {
    CustomerModal findById(long id);
    CustomerModal create(CustomerModal customer);
    CustomerModal update(long id ,CustomerModal customer);
    void delete(long id);

}