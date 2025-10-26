package com.uur.vetmanagement.dto.request.vaccination;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class VaccinationSaveRequest {

    private String name;

    @NotNull(message = "Hayvan ID'si zorunludur.")
    private Long animalId;

    @NotNull(message = "Aşı Türü ID'si zorunludur.")
    private Long vaccineTypeId;

    @NotNull(message = "Uygulama tarihi zorunludur.")
    private LocalDate vaccinationDate;
}
