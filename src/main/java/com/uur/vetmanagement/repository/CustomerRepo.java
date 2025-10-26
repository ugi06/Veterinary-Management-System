package com.uur.vetmanagement.repository;

import com.uur.vetmanagement.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {
    List<Customer> findByNameContainingIgnoreCase(String name);
}
