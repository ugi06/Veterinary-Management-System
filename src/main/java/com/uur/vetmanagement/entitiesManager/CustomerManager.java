package com.uur.vetmanagement.entitiesManager;

import com.uur.vetmanagement.core.config.modelMapper.IModelMapperService;
import com.uur.vetmanagement.dto.request.customer.CustomerSaveRequest;
import com.uur.vetmanagement.dto.request.customer.CustomerUpdateRequest;
import com.uur.vetmanagement.dto.response.customer.CustomerResponse;
import com.uur.vetmanagement.entity.Customer;
import com.uur.vetmanagement.service.abstracts.ICustomerService;
import com.uur.vetmanagement.service.concretes.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class CustomerManager {

    private final ICustomerService customerService;
    private final IModelMapperService modelMapper;

    public CustomerManager(ICustomerService customerService, IModelMapperService modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    // 1. SAVE
    public CustomerResponse saveCustomer(CustomerSaveRequest request) {
        Customer customer = modelMapper.forRequest().map(request, Customer.class);
        Customer savedCustomer = customerService.save(customer);
        return modelMapper.forResponse().map(savedCustomer, CustomerResponse.class);
    }

    // 2. GET BY ID (Sonsuz Döngü Çözümü)
    public CustomerResponse getCustomerById(Long id) {
        Customer customer = customerService.getById(id);
        // Entity'den DTO'ya dönüşüm yapılırken ilişkiler kesilir.
        return modelMapper.forResponse().map(customer, CustomerResponse.class);
    }

    // 3. FIND ALL
    public Page<CustomerResponse> getAllCustomers(Pageable pageable) {

        // 1. Service'ten sayfalanmış Entity listesini çek
        Page<Customer> customerPage = customerService.findAll(pageable);

        // 2. Entity sayfasını DTO sayfasına dönüştürme
        // Spring'de Page objesinin kendi 'map' metodu vardır.
        return customerPage.map(customer ->
                modelMapper.forResponse().map(customer, CustomerResponse.class));
    }

    // 4. UPDATE
    public CustomerResponse updateCustomer(Long id, CustomerUpdateRequest request) {
        Customer updatedCustomer = modelMapper.forRequest().map(request, Customer.class);
        Customer savedCustomer = customerService.update(id, updatedCustomer);
        return modelMapper.forResponse().map(savedCustomer, CustomerResponse.class);
    }

    // 5. DELETE
    public void deleteCustomer(Long id) {
        customerService.delete(id);
    }

    // 6. FILTER BY NAME
    public List<CustomerResponse> getCustomersByName(String name) {
        List<Customer> customers = customerService.findByName(name);
        return customers.stream()
                .map(customer -> modelMapper.forResponse().map(customer, CustomerResponse.class))
                .collect(Collectors.toList());
    }
}
