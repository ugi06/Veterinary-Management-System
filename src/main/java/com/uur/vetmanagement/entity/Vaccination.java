package com.uur.vetmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "vaccination")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vaccination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccination_id")
    private Long id;

    @Column(name = "vaccination_date", nullable = false)
    private LocalDate vaccinationDate;

    @Column(name = "protection_end_date", nullable = false)
    private LocalDate protectionEndDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vaccination_animal_id", referencedColumnName = "animal_id", nullable = false)
    private Animal animal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vaccination_vaccine_id", referencedColumnName = "vaccine_id", nullable = false)
    private Vaccine vaccineType;

}