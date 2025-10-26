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

    @Column(name = "animal_species",unique = true, nullable = false)
    private String species;

    @Column(name = "animal_breed")
    private String breed;

    @Column(name = "animal_gender",unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private gender gender;

    @Column(name = "animal_colour")
    private String colour;

    @Column(name = "animal_dateOfBirth")
    private LocalDate dateOfBirth;

    public enum gender {
        FEMALE,
        MALE
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_customer_id", referencedColumnName = "customer_id")
    private Customer animalCustomer;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "animal2doctor",
            joinColumns = @JoinColumn(name = "a2d_animal_id"),
            inverseJoinColumns = @JoinColumn(name = "a2d_doctor_id")
    )
    private List<Doctor> animalDoctors;

    @OneToMany(mappedBy = "animalAppointment",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private List<Appointment> appointmentList=new ArrayList<>();

    @OneToMany(mappedBy = "animal", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Vaccination> vaccinationHistory = new ArrayList<>();

}
