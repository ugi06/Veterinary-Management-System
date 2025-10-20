package com.uur.vetmanagement.service.abstracts;

import com.uur.vetmanagement.entity.Appointment;

import java.util.List;

public interface IAppointmentService {
    Appointment save (Appointment appointment);

    Appointment getById (Long id);

    List<Appointment> findAll();

    Appointment update (Long id, Appointment appointment);

    void delete(Long id);
}
