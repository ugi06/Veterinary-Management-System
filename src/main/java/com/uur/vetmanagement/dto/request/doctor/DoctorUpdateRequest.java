package com.uur.vetmanagement.dto.request.doctor;

import com.uur.vetmanagement.entity.Animal;
import com.uur.vetmanagement.entity.AvailableDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorUpdateRequest {

    private String name;
    private String phone;
    private String address;
    private String city;
    private List<Animal> animals;
    private List<AvailableDate> availableDateList;
}
