package com.uur.vetmanagement.dto.response.animal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalSummaryResponse {

    private Long id;
    private String name;
}
