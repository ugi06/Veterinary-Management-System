package com.uur.vetmanagement.dto.response.vaccination;

import com.uur.vetmanagement.dto.response.animal.AnimalSummaryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccinationResponse {

    private Long id;

    // Entity'den gelen tarihler
    private LocalDate vaccinationDate;
    private LocalDate protectionEndDate; // 📌 Hesaplanan Bitiş Tarihi

    // İlişkiler (Özet DTO'lar ile temsil edilir)
    private AnimalSummaryResponse animal;
    private VaccineSummaryResponse vaccineType;
}
