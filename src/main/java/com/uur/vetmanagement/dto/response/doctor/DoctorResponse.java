package com.uur.vetmanagement.dto.response.doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponse {
    private Long id;

    // Temel Bilgiler
    private String name;
    private String phone;
    private String email;
    private String address;
    private String city;
}
