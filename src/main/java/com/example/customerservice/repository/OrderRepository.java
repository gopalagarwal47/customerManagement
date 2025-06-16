package com.example.customerservice.repository;

import com.example.customerservice.model.OrderModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderModal, Long> {

    // 1) Fetch all orders for a given customer
    @Query("SELECT o FROM OrderModal o WHERE o.customer.id = :customerId")
    List<OrderModal> findOrdersByCustomerId(@Param("customerId") Long customerId);

    // 2) Update an order
    @Modifying
    @Transactional
    @Query("UPDATE OrderModal o "
            + "SET o.productName = ?1, o.quantity = ?2, o.price = ?3, o.orderDate = ?4 "
            + "WHERE o.id = ?5")
    int updateOrderByHql(
            String productName,
            Integer quantity,
            Double price,
            LocalDate orderDate,
            Long id
    );

    // 3) Delete an order
    @Modifying
    @Transactional
    @Query("DELETE FROM OrderModal o WHERE o.id = ?1")
    int deleteOrderByHql(Long id);
}
