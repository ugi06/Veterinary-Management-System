package com.uur.vetmanagement.service.concretes;

import com.uur.vetmanagement.core.config.modelMapper.IModelMapperService;
import com.uur.vetmanagement.entity.Vaccine;
import com.uur.vetmanagement.repository.VaccineRepo;
import com.uur.vetmanagement.service.abstracts.IVaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class VaccineService implements IVaccineService {

    private final VaccineRepo vaccineRepo;
    private final IModelMapperService modelMapper;


    @Autowired
    public VaccineService(VaccineRepo vaccineRepo, IModelMapperService modelMapper) {
        this.vaccineRepo = vaccineRepo;
        this.modelMapper = modelMapper;
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
        existingVaccine.setDurationInDays(vaccine.getDurationInDays());
        return vaccineRepo.save(existingVaccine);
    }

    @Override
    public void delete(Long id) {
        this.vaccineRepo.deleteById(id);
    }
}
