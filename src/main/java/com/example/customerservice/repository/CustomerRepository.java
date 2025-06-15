package com.example.customerservice.repository;


import com.example.customerservice.model.CustomerModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.time.LocalDate;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModal, Long>{

    @Query(value = "SELECT * FROM customer", nativeQuery = true)
    List<CustomerModal> findAllCustomers();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO customer (first_name, last_name, email, image, phone, address, birth_date) " +
            "VALUES (:firstName, :lastName, :email, :image, :phone, :address, :birthDate)", nativeQuery = true)
    void insertCustomer(@Param("firstName") String firstName,
                        @Param("lastName") String lastName,
                        @Param("email") String email,
                        @Param("image") String image,
                        @Param("phone") String phone,
                        @Param("address") String address,
                        @Param("birthDate") LocalDate birthDate);

    @Query(value = "SELECT * FROM customer WHERE email = :email ORDER BY id DESC LIMIT 1", nativeQuery = true)
    CustomerModal findLatestInsertedCustomerByEmail(@Param("email") String email);
}