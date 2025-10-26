package com.uur.vetmanagement.dto.response.customer;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSummaryResponse {
    private Long id;
    private String name;
}
