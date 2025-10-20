package com.uur.vetmanagement.service.abstracts;

import com.uur.vetmanagement.entity.Doctor;

import java.util.List;

public interface IDoctorService {

    Doctor save (Doctor doctor);

    Doctor getById (Long id);

    List<Doctor> findAll();

    Doctor update (Long id, Doctor doctor);

    void delete(Long id);
}
