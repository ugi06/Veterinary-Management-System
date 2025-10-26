package com.uur.vetmanagement.dto.response.animal;

import com.uur.vetmanagement.dto.response.customer.CustomerSummaryResponse;
import com.uur.vetmanagement.dto.response.doctor.DoctorSummaryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalResponse {
    private Long id;
    private String name;
    private String species;
    private CustomerSummaryResponse animalCustomer;
    private List<DoctorSummaryResponse> animalDoctors;
}
