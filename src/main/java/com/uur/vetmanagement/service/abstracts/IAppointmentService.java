package com.uur.vetmanagement.service.abstracts;

import com.uur.vetmanagement.dto.request.appointment.AppointmentSaveRequest;
import com.uur.vetmanagement.dto.request.appointment.AppointmentUpdateRequest;
import com.uur.vetmanagement.dto.response.appointment.AppointmentResponse;
import com.uur.vetmanagement.entity.Appointment;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAppointmentService {

    AppointmentResponse save (AppointmentSaveRequest appointmentSaveRequest);

    AppointmentResponse getById (Long id);

    Page<Appointment> cursor(int page, int pageSize);

    AppointmentResponse update (Long id, AppointmentUpdateRequest appointmentUpdateRequest);

    void delete(Long id);
}
