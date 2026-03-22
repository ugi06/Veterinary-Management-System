package com.uur.vetmanagement.service.abstracts;

import com.uur.vetmanagement.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {

    Customer save(Customer customer);
    Customer getById(Long id);
    Page<Customer> findAll(Pageable pageable);
    Customer update(Long id, Customer customer);
    void delete(Long id);
    // Filtreleme için Manager'ın çağıracağı metot
    List<Customer> findByName(String name);
}
