package com.example.customerservice.service;

import com.example.customerservice.model.CustomerModal;
import com.example.customerservice.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomerService implements Serviceinterface{


    private final CustomerRepository repository;


    @Override
    public CustomerModal findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<CustomerModal> getAllCustomers() {
        return repository.findAllCustomers();
    }

    @Override
    public CustomerModal create(CustomerModal customer) {
        return repository.save(customer);
    }

    @Override
    public CustomerModal update(long id, CustomerModal customer) {
        Optional<CustomerModal> existing = repository.findById(id);
        if (existing.isPresent()) {
            CustomerModal existingCustomer = existing.get();
            existingCustomer.setFirstName(customer.getFirstName());
            existingCustomer.setLastName(customer.getLastName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPhone(customer.getPhone());
            existingCustomer.setAddress(customer.getAddress());
            existingCustomer.setImage(customer.getImage());
            existingCustomer.setBirthDate(customer.getBirthDate());
            return repository.save(existingCustomer);
        }
        return null;
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }


}