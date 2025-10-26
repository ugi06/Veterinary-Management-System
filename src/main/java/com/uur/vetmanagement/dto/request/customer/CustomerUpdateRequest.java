package com.uur.vetmanagement.dto.request.customer;

import com.uur.vetmanagement.entity.Animal;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateRequest {

    @NotNull
    private String name;
    @NotNull
    private String phone;

    private  String address;
    private  String city;
    private List<Animal> animals;

}
