package com.example.customerservice.service;

import com.example.customerservice.model.OrderModal;
import com.example.customerservice.model.CustomerModal;
import com.example.customerservice.repository.OrderRepository;
import com.example.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderInterface {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Override
    public List<OrderModal> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderModal> getOrdersForCustomer(Long customerId) {
        return orderRepository.findOrdersByCustomerId(customerId);
    }

    @Override
    public OrderModal createOrder(Long customerId, OrderModal order) {
        Optional<CustomerModal> custOpt = customerRepository.findById(customerId);
        if (custOpt.isEmpty()) {
            return null;
        }
        order.setCustomer(custOpt.get());
        return orderRepository.save(order);
    }

    @Override
    public OrderModal getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public OrderModal updateOrder(Long id, OrderModal data) {
        int rowsAffected = orderRepository.updateOrderByHql(
                data.getProductName(),
                data.getQuantity(),
                data.getPrice(),
                data.getOrderDate(),
                id
        );
        if (rowsAffected > 0) {
            return orderRepository.findById(id).orElse(null);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        orderRepository.deleteOrderByHql(id);
    }
}
