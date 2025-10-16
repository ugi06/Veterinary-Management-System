package com.uur.vetmanagement.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccine_id")
    private Long id;

    @Column(name="vaccine_name")
    private String name;

    @Column(name = "vaccine_code")
    private String code;

    @Column(name = "vaccine_start")
    private LocalDate protectionStartDate;

    @Column(name = "vaccine_end")
    private LocalDate protectionFinishDate;

    @ManyToMany(mappedBy = "vaccineList",cascade ={CascadeType.PERSIST,CascadeType.MERGE})
    private List<Animal> animalList = new ArrayList<>();
}
