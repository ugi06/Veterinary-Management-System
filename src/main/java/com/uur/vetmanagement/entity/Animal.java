package com.uur.vetmanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "animals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private Long id;

    @Column(name = "animal_name")
    private String name;

    @Column(name = "animal_species")
    @NotNull
    private String species;

    @Column(name = "animal_breed")
    @NotNull
    private boolean breed;

    @Column(name = "animal_gender")
    @NotNull
    private gender gender;

    @Column(name = "animal_colour")
    private String colour;

    @Column(name = "animal_dateOfBirth")
    private LocalDate dateOfBirth;

    enum gender {
        FEMALE,
        MALE
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_customer_id", referencedColumnName = "customer_id")
    private Customer animalCustomer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_doctor_id", referencedColumnName = "doctor_id")
    private Doctor animalDoctor;

    @OneToMany(mappedBy = "animalAppointment",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private List<Appointment> appointmentList=new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY,cascade ={CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(
            name = "animal2vaccine",
            joinColumns = {@JoinColumn(name = "a2v_animal_id")},
            inverseJoinColumns = {@JoinColumn(name = "a2v_vaccine_id")}
    )
    private List<Vaccine> vaccineList ;
}
