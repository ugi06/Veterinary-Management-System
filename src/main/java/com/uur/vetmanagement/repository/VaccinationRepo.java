package com.uur.vetmanagement.repository;

import com.uur.vetmanagement.entity.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VaccinationRepo extends JpaRepository<Vaccination,Long> {
    Optional<Vaccination> findFirstByAnimalIdAndVaccineTypeCodeAndProtectionEndDateGreaterThan(
            Long animalId,
            String code,
            LocalDate date
    );

    // 2. İSTER (Hayvan ID'sine göre listeleme)
    List<Vaccination> findByAnimalId(Long animalId);

    // 3. İSTER (Koruyuculuk bitiş tarihine göre aralıkta listeleme)
    // ProtectionEndDate alanının verilen aralıkta olup olmadığını kontrol eder.
    List<Vaccination> findByProtectionEndDateBetween(LocalDate startDate, LocalDate endDate);
}
