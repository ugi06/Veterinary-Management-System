package com.uur.vetmanagement.dto.request.animal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalUpdateRequest {

    // Hayvanın temel bilgileri
    @NotNull(message = "Hayvan adı boş bırakılamaz.")
    private String name;

    @NotBlank
    private String species;
    private String breed;
    private String gender;
    private String colour;
    private LocalDate dateOfBirth; // java.time.LocalDate kullanılır

    // İlişki: Sahibinin ID'si (Foreign Key)
    @NotNull(message = "Sahip ID'si zorunludur.")
    private Long customerId;

    private List<Long> doctorIds;

}
