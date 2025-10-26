package com.uur.vetmanagement.dto.response.doctor;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DoctorSummaryResponse {
    private Long id;
    private String name;
}
