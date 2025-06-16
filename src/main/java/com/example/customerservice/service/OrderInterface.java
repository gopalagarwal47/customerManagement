package com.example.customerservice.service;
import com.example.customerservice.model.OrderModal;

import java.util.List;

public interface OrderInterface {
    List<OrderModal> getAllOrders();
    List<OrderModal> getOrdersForCustomer(Long customerId);
    OrderModal getOrderById(Long id);
    OrderModal createOrder(Long customerId, OrderModal order);
    OrderModal updateOrder(Long id, OrderModal order);
    void deleteOrder(Long id);
}
