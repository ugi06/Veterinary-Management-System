package com.uur.vetmanagement.service.concretes;

import com.uur.vetmanagement.entity.Vaccine;
import com.uur.vetmanagement.repository.VaccineRepo;
import com.uur.vetmanagement.service.abstracts.IVaccineService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class VaccineService implements IVaccineService {

    private final VaccineRepo vaccineRepo;

    @Autowired
    public VaccineService(VaccineRepo vaccineRepo) {
        this.vaccineRepo = vaccineRepo;
    }


    @Override
    public Vaccine save(Vaccine vaccine) {
        return this.vaccineRepo.save(vaccine);
    }

    @Override
    public Vaccine getById(Long id) {
        return this.vaccineRepo.findById(id).orElseThrow();
    }

    @Override
    public List<Vaccine> findAll() {
        return this.vaccineRepo.findAll();
    }

    @Override
    public Vaccine update(Long id, Vaccine vaccine) {
        Vaccine existingVaccine = vaccineRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        existingVaccine.setName(vaccine.getName());
        existingVaccine.setCode(vaccine.getCode());
        existingVaccine.setProtectionStartDate(vaccine.getProtectionStartDate());
        existingVaccine.setProtectionFinishDate(vaccine.getProtectionFinishDate());

        return vaccineRepo.save(existingVaccine);
    }

    @Override
    public void delete(Long id) {
        this.vaccineRepo.deleteById(id);
    }
}
