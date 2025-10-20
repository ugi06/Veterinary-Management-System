package com.uur.vetmanagement.service.concretes;

import com.uur.vetmanagement.entity.Customer;
import com.uur.vetmanagement.repository.CustomerRepo;
import com.uur.vetmanagement.service.abstracts.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepo customerRepo;

    @Autowired
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public Customer save(Customer customer) {
        return this.customerRepo.save(customer);
    }

    @Override
    public Customer getById(Long id) {
        return this.customerRepo.findById(id).orElseThrow();
    }

    @Override
    public List<Customer> findAll() {
        return this.customerRepo.findAll();
    }

    @Override
    public Customer update(Long id, Customer customer) {
        Customer existingCustomer = customerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        existingCustomer.setName(customer.getName());
        existingCustomer.setPhone(customer.getPhone());
        existingCustomer.setAddress(customer.getAddress());
        existingCustomer.setCity(customer.getCity());
        existingCustomer.setAnimals(customer.getAnimals());

        return customerRepo.save(existingCustomer);
    }

    @Override
    public void delete(Long id) {
        this.customerRepo.deleteById(id);
    }
}
