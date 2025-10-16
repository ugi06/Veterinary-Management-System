package com.uur.vetmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Long id ;

    @Column(name = "doctor_name")
    private String name;

    @Column(name = "doctor_phone")
    private String phone;

    @Column(name = "doctor_email")
    private String email;

    @Column(name = "doctor_address")
    private String address;

    @Column(name = "doctor_city")
    private String city;

    @OneToMany(mappedBy = "animalDoctor",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private List<Animal> animalList = new ArrayList<>();

    @OneToMany(mappedBy = "doctorAvailability",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private List<AvailableDate> availableDateList=new ArrayList<>();


}
