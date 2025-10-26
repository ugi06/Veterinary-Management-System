package com.uur.vetmanagement.dto.request.customer;

import com.uur.vetmanagement.entity.Animal;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSaveRequest {

    @NotNull
    private String name;
    @NotNull
    private String phone;
    @NotNull
    @Email
    private  String email;
    private  String address;
    private  String city;
    private List<Animal> animals;

}
