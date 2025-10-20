package com.uur.vetmanagement.service.concretes;

import com.uur.vetmanagement.entity.Appointment;
import com.uur.vetmanagement.repository.AppointmentRepo;
import com.uur.vetmanagement.service.abstracts.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AppointmentService implements IAppointmentService {

    private final AppointmentRepo appointmentRepo;

    @Autowired
    public AppointmentService(AppointmentRepo appointmentRepo) {
        this.appointmentRepo = appointmentRepo;
    }

    @Override
    public Appointment save(Appointment appointment) {
        return this.appointmentRepo.save(appointment);
    }

    @Override
    public Appointment getById(Long id) {
        return this.appointmentRepo.findById(id).orElseThrow(); // Exception ekle
    }

    @Override
    public List<Appointment> findAll() {
        return this.appointmentRepo.findAll();
    }

    @Override
    public Appointment update(Long id, Appointment appointment) {
        Appointment existingAppointment = appointmentRepo.findById(id).orElseThrow();// Exception ekle
        existingAppointment.setAppointmentDate(appointment.getAppointmentDate());
        return this.appointmentRepo.save(existingAppointment);
    }

    @Override
    public void delete(Long id) {
        this.appointmentRepo.deleteById(id);
    }
}
