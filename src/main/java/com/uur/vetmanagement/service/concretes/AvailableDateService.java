package com.uur.vetmanagement.service.concretes;

import com.uur.vetmanagement.core.config.modelMapper.IModelMapperService;
import com.uur.vetmanagement.dto.request.availableDate.AvailableDateSaveRequest;
import com.uur.vetmanagement.dto.response.availableDate.AvailableDateResponse;
import com.uur.vetmanagement.entity.AvailableDate;
import com.uur.vetmanagement.entity.Doctor;
import com.uur.vetmanagement.repository.AvailableDateRepo;
import com.uur.vetmanagement.repository.DoctorRepo;
import com.uur.vetmanagement.service.abstracts.IAnimalService;
import com.uur.vetmanagement.service.abstracts.IAvailableDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AvailableDateService implements IAvailableDateService {

    private final AvailableDateRepo availableDateRepo;
    private final IModelMapperService modelMapper;
    private final DoctorRepo doctorRepo;


    @Autowired
    public AvailableDateService(AvailableDateRepo availableDateRepo, IModelMapperService modelMapper, DoctorRepo doctorRepo) {
        this.availableDateRepo = availableDateRepo;
        this.modelMapper = modelMapper;
        this.doctorRepo = doctorRepo;
    }


    @Override
    public AvailableDateResponse save(AvailableDateSaveRequest availableDateSaveRequest) {
        Doctor doctor = doctorRepo.findById(availableDateSaveRequest.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doktor bulunamadı. ID: " + availableDateSaveRequest.getDoctorId()));

        AvailableDate availableDateToSave = this.modelMapper.forRequest().map(availableDateSaveRequest, AvailableDate.class);

        availableDateToSave.setDoctorAvailability(doctor);
        availableDateToSave.setId(null);

        AvailableDate savedDate = this.availableDateRepo.save(availableDateToSave);

        return this.modelMapper.forResponse().map(savedDate, AvailableDateResponse.class);
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
