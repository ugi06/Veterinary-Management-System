package com.uur.vetmanagement.dto.request.doctor;

import com.uur.vetmanagement.entity.Animal;
import com.uur.vetmanagement.entity.AvailableDate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorSaveRequest {
    @NotNull
    private String name;
    @NotNull
    private String phone;
    @NotNull
    private String email;
    private String address;
    private String city;
    private List<Animal> animals;
    private List<AvailableDate> availableDateList;
}
