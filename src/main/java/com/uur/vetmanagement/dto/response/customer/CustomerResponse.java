package com.uur.vetmanagement.dto.response.customer;

import com.uur.vetmanagement.dto.response.animal.AnimalResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private Long id;
    private String name;
    private String city;
    private List<AnimalResponse> animals;
}

