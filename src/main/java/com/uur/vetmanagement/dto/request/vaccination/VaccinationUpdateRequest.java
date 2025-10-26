package com.uur.vetmanagement.dto.request.vaccination;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccinationUpdateRequest {

    private LocalDate vaccinationDate;

    // İlişki ID'leri
    private Long animalId;
    private Long vaccineTypeId;
}

