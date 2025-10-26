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
public class AnimalSaveRequest {

    @NotBlank(message = "Hayvan adı boş bırakılamaz.")
    private String name;

    @NotBlank(message = "Tür alanı boş bırakılamaz.")
    private String species;

    private String breed;
    private String colour;
    private LocalDate dateOfBirth;

    // 📌 Enum Alanı (JSON'dan String olarak geleceği için String tipinde tutulur)
    @NotBlank(message = "Cinsiyet alanı zorunludur.")
    private String gender;

    // İlişkiler (ID'ler ile kurulur)
    @NotNull(message = "Sahip (Müşteri) ID'si zorunludur.")
    private Long customerId;

    // Birçok doktor atanabilir (zorunlu olmayabilir)
    private List<Long> doctorIds;

}
