package com.uur.vetmanagement.dto.response.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AppointmentResponse {

    private Long id;
    private LocalDateTime appointmentDate;
}
