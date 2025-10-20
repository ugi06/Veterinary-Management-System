package com.uur.vetmanagement.service.abstracts;

import com.uur.vetmanagement.entity.Customer;

import java.util.List;

public interface ICustomerService {

    // POST
    Customer save(Customer customer);

    //GET BY ID
    Customer getById(Long id);

    //FIND ALL
    List<Customer> findAll();

    // UPDATE
    Customer update(Long id, Customer customer);

    // DELETE
    void delete(Long id);
}
