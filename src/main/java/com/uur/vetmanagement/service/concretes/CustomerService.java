package com.uur.vetmanagement.service.concretes;

import com.uur.vetmanagement.entity.Customer;
import com.uur.vetmanagement.repository.CustomerRepo;
import com.uur.vetmanagement.service.abstracts.ICustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepository) {
        this.customerRepo = customerRepository;
    }

    // ... (Diğer CRUD metodları varlık kontrolü ile buraya eklenecektir, önceki cevaplarda olduğu gibi)

    @Override
    public Customer save(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public Customer getById(Long id) {
        // Varlık kontrolü
        return customerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Müşteri ID'si bulunamadı: " + id));
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        // 📌 JpaRepository'nin default findAll metodunu kullanır.
        return customerRepo.findAll(pageable);
    }

    // update ve delete metodları buraya eklenecektir.

    @Override
    public Customer update(Long id, Customer customer) {
        if (!customerRepo.existsById(id)) {
            throw new RuntimeException("Güncellenmek istenen müşteri ID'si bulunamadı: " + id);
        }
        customer.setId(id);
        return customerRepo.save(customer);
    }

    @Override
    public void delete(Long id) {
        if (!customerRepo.existsById(id)) {
            throw new RuntimeException("Silinmek istenen müşteri ID'si bulunamadı: " + id);
        }
        customerRepo.deleteById(id);
    }

    // Filtreleme Metodu
    @Override
    public List<Customer> findByName(String name) {
        return customerRepo.findByNameContainingIgnoreCase(name);
    }
}
