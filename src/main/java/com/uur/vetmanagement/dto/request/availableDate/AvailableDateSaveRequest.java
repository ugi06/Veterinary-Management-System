package com.uur.vetmanagement.dto.request.availableDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateSaveRequest {
    @NotNull(message = "Müsaitlik tarihi zorunludur.")
    private LocalDate date;

    @NotNull(message = "Doktor ID'si zorunludur.")
    private Long doctorId;
}
