package com.uur.vetmanagement.service.concretes;

import com.uur.vetmanagement.entity.AvailableDate;
import com.uur.vetmanagement.repository.AvailableDateRepo;
import com.uur.vetmanagement.service.abstracts.IAvailableDateService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AvailableDateService implements IAvailableDateService {

    private final AvailableDateRepo availableDateRepo;

    @Autowired
    public AvailableDateService(AvailableDateRepo availableDateRepo) {
        this.availableDateRepo = availableDateRepo;
    }


    @Override
    public AvailableDate save(AvailableDate availableDate) {
        return this.availableDateRepo.save(availableDate);
    }

    @Override
    public AvailableDate getById(Long id) {
        return this.availableDateRepo.findById(id).orElseThrow(); // exception ekle
    }

    @Override
    public List<AvailableDate> findAll() {
        return this.availableDateRepo.findAll();
    }

    @Override
    public AvailableDate update(Long id, AvailableDate availableDate) {
        AvailableDate existingAvailableDate = availableDateRepo.findById(id).orElseThrow(); // exception ekle
        existingAvailableDate.setAvailableDate(availableDate.getAvailableDate());
        return this.availableDateRepo.save(existingAvailableDate);
    }

    @Override
    public void delete(Long id) {
        this.availableDateRepo.deleteById(id);

    }
}
