package com.uur.vetmanagement.controller;

import com.uur.vetmanagement.dto.request.appointment.AppointmentSaveRequest;
import com.uur.vetmanagement.dto.response.appointment.AppointmentResponse;
import com.uur.vetmanagement.entity.Appointment;
import com.uur.vetmanagement.service.abstracts.IAnimalService;
import com.uur.vetmanagement.service.abstracts.IAppointmentService;
import com.uur.vetmanagement.service.concretes.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/appointments")
public class AppointmentController {

    private final IAppointmentService appointmentService;
    private final IAnimalService modelMapper;


    public AppointmentController(IAppointmentService appointmentService, IAnimalService modelMapper) {
        this.appointmentService = appointmentService;
        this.modelMapper = modelMapper;
    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentResponse save(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest) {
        return this.appointmentService.save(appointmentSaveRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Appointment getById(@PathVariable Long id) {
        return this.appointmentService.getById(id);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Appointment> findAll() {
        return this.appointmentService.findAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Appointment update(@PathVariable Long id, @RequestBody Appointment appointment) {
        return this.appointmentService.update(id,appointment);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        this.appointmentService.delete(id);
    }

}
