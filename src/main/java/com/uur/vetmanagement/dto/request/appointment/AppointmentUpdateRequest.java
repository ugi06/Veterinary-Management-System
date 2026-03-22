package com.uur.vetmanagement.dto.request.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentUpdateRequest {
    private LocalDateTime appointmentDate;
    private Long animalId;
}
