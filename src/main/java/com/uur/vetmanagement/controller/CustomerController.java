package com.uur.vetmanagement.controller;

import com.uur.vetmanagement.core.result.ResultData;

import com.uur.vetmanagement.dto.request.customer.CustomerSaveRequest;
import com.uur.vetmanagement.dto.request.customer.CustomerUpdateRequest;
import com.uur.vetmanagement.dto.response.customer.CustomerResponse;
import com.uur.vetmanagement.entitiesManager.CustomerManager;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

    private final CustomerManager customerManager;

    public CustomerController(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    // 1. SAVE (POST)
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerResponse> save(@Valid @RequestBody CustomerSaveRequest request) {
        CustomerResponse response = this.customerManager.saveCustomer(request);
        return ResultData.created(response);
    }

    // 2. GET BY ID (GET) - DTO döndürülerek sonsuz döngü engellenir.
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> getById(@PathVariable("id") Long id) {
        CustomerResponse response = this.customerManager.getCustomerById(id);
        return ResultData.success(response);
    }

    // 3. FIND ALL (GET)
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
// 📌 Parametre olarak Pageable objesi eklenir.
    public ResultData<Page<CustomerResponse>> findAll(Pageable pageable) {

        // 📌 Manager metodunu Pageable ile çağır
        Page<CustomerResponse> response = this.customerManager.getAllCustomers(pageable);

        // ResultData'nın Page<T> tipini desteklediği varsayılmıştır.
        return ResultData.success(response);
    }

    // 4. UPDATE (PUT)
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody CustomerUpdateRequest request) {

        CustomerResponse response = this.customerManager.updateCustomer(id, request);
        return ResultData.success(response);
    }

    // 5. DELETE (DELETE)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        this.customerManager.deleteCustomer(id);
    }

    // 6. FILTER BY NAME (GET)
    @GetMapping("/by-name")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<CustomerResponse>> getCustomersByName(@RequestParam String name) {
        List<CustomerResponse> response = this.customerManager.getCustomersByName(name);
        return ResultData.success(response);
    }
}