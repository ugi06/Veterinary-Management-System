package com.uur.vetmanagement.service.abstracts;

import com.uur.vetmanagement.dto.request.appointment.AppointmentSaveRequest;
import com.uur.vetmanagement.dto.response.appointment.AppointmentResponse;
import com.uur.vetmanagement.entity.Appointment;

import java.util.List;

public interface IAppointmentService {
    AppointmentResponse save (AppointmentSaveRequest appointmentSaveRequest);

    Appointment getById (Long id);

    List<Appointment> findAll();

    Appointment update (Long id, Appointment appointment);

    void delete(Long id);
}
