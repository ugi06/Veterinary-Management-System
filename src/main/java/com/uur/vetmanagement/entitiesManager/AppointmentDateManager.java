package com.uur.vetmanagement.entitiesManager;

import com.uur.vetmanagement.core.config.modelMapper.IModelMapperService;
import com.uur.vetmanagement.dto.request.appointment.AppointmentSaveRequest;
import com.uur.vetmanagement.dto.request.appointment.AppointmentUpdateRequest;
import com.uur.vetmanagement.dto.response.appointment.AppointmentResponse;
import com.uur.vetmanagement.entity.Appointment;
import com.uur.vetmanagement.service.concretes.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppointmentDateManager {

    private final AppointmentService appointmentService;
    private final IModelMapperService modelMapper;

    public AppointmentDateManager(AppointmentService appointmentService, IModelMapperService modelMapper) {
        this.appointmentService = appointmentService;
        this.modelMapper = modelMapper;
    }


    public AppointmentResponse save(@Valid AppointmentSaveRequest appointmentSaveRequest) {

        this.appointmentService.save(appointmentSaveRequest);

        return this.modelMapper.forResponse().map(appointmentSaveRequest,AppointmentResponse.class);

    }


    public AppointmentResponse getById(Long id) {
        return this.appointmentService.getById(id);
    }

    public Page<AppointmentResponse> cursor(int page, int pageSize) {

        Page<Appointment> appointmentsList = this.appointmentService.cursor(page, pageSize);


        return appointmentsList
                .map(appointment -> this.modelMapper.forResponse().map(appointment, AppointmentResponse.class));
    }

    public AppointmentResponse update(Long id, AppointmentUpdateRequest appointmentUpdateRequest) {
        return appointmentService.update(id, appointmentUpdateRequest);
    }


    public void delete(Long id) {
        this.appointmentService.delete(id);
    }
}
