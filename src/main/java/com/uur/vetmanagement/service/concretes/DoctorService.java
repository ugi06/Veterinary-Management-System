package com.uur.vetmanagement.service.concretes;

import com.uur.vetmanagement.entity.Doctor;
import com.uur.vetmanagement.repository.DoctorRepo;
import com.uur.vetmanagement.service.abstracts.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
    public List<Doctor> findAll() {
        return this.doctorRepo.findAll();
    }

    @Override
    public Doctor update(Long id, Doctor doctor) {
        Doctor existingDoctor = doctorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
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
