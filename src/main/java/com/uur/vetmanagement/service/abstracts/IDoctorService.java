package com.uur.vetmanagement.service.abstracts;

import com.uur.vetmanagement.dto.request.doctor.DoctorSaveRequest;
import com.uur.vetmanagement.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDoctorService {

    Doctor save(Doctor doctor);            // Create
    Doctor getById(Long id);
    Page<Doctor> cursor(int page, int pageSize);;                // Read (Tüm kayıtlar)
    Doctor update(Long id ,Doctor doctor);          // Update
    void delete(Long id);
}
