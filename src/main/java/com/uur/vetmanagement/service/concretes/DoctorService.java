package com.uur.vetmanagement.service.concretes;

import com.uur.vetmanagement.core.config.modelMapper.IModelMapperService;
import com.uur.vetmanagement.entity.Animal;
import com.uur.vetmanagement.entity.Doctor;
import com.uur.vetmanagement.repository.DoctorRepo;
import com.uur.vetmanagement.service.abstracts.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class DoctorService implements IDoctorService {

    private final DoctorRepo doctorRepo;


    @Autowired
    public DoctorService(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    @Override
    public Doctor save(Doctor doctor) {
        return this.doctorRepo.save(doctor);
    }

    @Override
    public Doctor getById(Long id) {
        return this.doctorRepo.findById(id).orElseThrow();
    }

    @Override
    public Page<Doctor> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.doctorRepo.findAll(pageable);
    }


    @Override
    public Doctor update(Long id, Doctor doctor) {
        Doctor existingDoctor = doctorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + doctor.getId()));
        existingDoctor.setName(doctor.getName());
        existingDoctor.setPhone(doctor.getPhone());
        existingDoctor.setEmail(doctor.getEmail());
        existingDoctor.setAddress(doctor.getAddress());
        existingDoctor.setCity(doctor.getCity());
        return doctorRepo.save(existingDoctor);

    }

    @Override
    public void delete(Long id) {
        this.doctorRepo.deleteById(id);
    }
}
