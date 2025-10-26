package com.uur.vetmanagement.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Long id ;

    @Column(name = "doctor_name")
    @NotNull
    private String name;

    @Column(name = "doctor_phone",unique = true)
    private String phone;

    @Column(name = "doctor_email",unique = true,nullable = false)
    @Email
    private String email;

    @Column(name = "doctor_address")
    private String address;

    @Column(name = "doctor_city")
    private String city;

    @ManyToMany(mappedBy = "animalDoctors", fetch = FetchType.LAZY)
    private List<Animal> animals;

    @OneToMany(mappedBy = "doctorAvailability",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private List<AvailableDate> availableDateList=new ArrayList<>();


}
