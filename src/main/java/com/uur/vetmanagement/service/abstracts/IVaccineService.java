package com.uur.vetmanagement.service.abstracts;

import com.uur.vetmanagement.entity.Vaccine;

import java.util.List;

public interface IVaccineService {

    Vaccine save (Vaccine vaccine);

    Vaccine getById (Long id);

    List<Vaccine> findAll();

    Vaccine update (Long id, Vaccine vaccine);

    void delete(Long id);
}
