package com.example.customerservice.service;

import com.example.customerservice.model.CustomerModal;
import com.example.customerservice.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;




@Service
@RequiredArgsConstructor
public class CustomerService implements Serviceinterface{


    private final CustomerRepository repository;


    @Override
    public CustomerModal findById(long id) {
        return repository.findById(id);
    }

    public customerModal getAllCustomers(){
        return repository.findAllCustomers()
    }

    @Override
    public CustomerModal create(CustomerModal customer) {
        repository.insertCustomer(customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getImage(), customer.getPhone(), customer.getAddress(), customer.getBirthDate());
        // Workaround: you could return last inserted by `SELECT LAST_INSERT_ID()` logic if needed
        return customer;
    }

    @Override
    public CustomerModal update(long id, CustomerModal customer) {
        Optional<CustomerModal> existing = repository.findById(id);
        if (existing.isPresent()) {
            CustomerModal existingCustomer = existing.get();
            // copy relevant fields
            existingCustomer.setName(customer.getName()); // example, use real fields
            existingCustomer.setEmail(customer.getEmail()); // update your fields accordingly
            return repository.save(existingCustomer);
        }
        return null; // or throw custom NotFoundException
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }


}