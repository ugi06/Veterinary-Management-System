package com.uur.vetmanagement.dto.request.appointment;

import com.uur.vetmanagement.entity.Animal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentSaveRequest {


    private LocalDateTime appointmentDate;

    private Long animalId;

}
