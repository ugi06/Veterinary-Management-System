package com.uur.vetmanagement.service.abstracts;

import com.uur.vetmanagement.dto.request.vaccination.VaccinationSaveRequest;
import com.uur.vetmanagement.dto.response.vaccination.VaccinationResponse;
import com.uur.vetmanagement.entity.Vaccination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface IVaccinationService {



    Vaccination save(Vaccination vaccination);

    Vaccination getById(Long id);

    Page<Vaccination> findAll(Pageable pageable);

    Vaccination update(Long id ,Vaccination vaccination);

    void delete(Long id);

    List<Vaccination> findByAnimalId(Long animalId);

    // 3. İSTER: Tarih aralığına göre listeleme
    List<Vaccination> findByProtectionPeriod(LocalDate startDate, LocalDate endDate);
}
