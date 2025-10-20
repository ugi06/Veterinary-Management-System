package com.uur.vetmanagement.controller;


import com.uur.vetmanagement.entity.Customer;
import com.uur.vetmanagement.service.abstracts.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

    private final ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer save(@Valid @RequestBody Customer customer) {
        return this.customerService.save(customer);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getById(@PathVariable Long id) {
        return this.customerService.getById(id);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> findAll() {
        return this.customerService.findAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Customer update(@PathVariable Long id, @RequestBody Customer customer) {
        return this.customerService.update(id, customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        this.customerService.delete(id);
    }


}

