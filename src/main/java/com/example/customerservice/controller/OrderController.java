package com.example.customerservice.controller;

import com.example.customerservice.model.OrderModal;
import com.example.customerservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderModal>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderModal> getOrderById(@PathVariable Long id) {
        OrderModal order = orderService.getOrderById(id);
        return order != null
                ? ResponseEntity.ok(order)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/by-customer/{customerId}")
    public ResponseEntity<List<OrderModal>> getOrdersByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(orderService.getOrdersForCustomer(customerId));
    }

    @PostMapping("/create/{customerId}")
    public ResponseEntity<OrderModal> createOrder(
            @PathVariable Long customerId,
            @RequestBody OrderModal order
    ) {
        OrderModal created = orderService.createOrder(customerId, order);
        if (created == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderModal> updateOrder(
            @PathVariable Long id,
            @RequestBody OrderModal order
    ) {
        OrderModal updated = orderService.updateOrder(id, order);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order deleted successfully");
    }
}
