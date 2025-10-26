package com.uur.vetmanagement.service.concretes;

import com.uur.vetmanagement.core.config.modelMapper.IModelMapperService;
import com.uur.vetmanagement.dto.request.appointment.AppointmentSaveRequest;
import com.uur.vetmanagement.dto.response.appointment.AppointmentResponse;
import com.uur.vetmanagement.entity.Animal;
import com.uur.vetmanagement.entity.Appointment;
import com.uur.vetmanagement.repository.AnimalRepo;
import com.uur.vetmanagement.repository.AppointmentRepo;
import com.uur.vetmanagement.service.abstracts.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService implements IAppointmentService {

    private final AppointmentRepo appointmentRepo;
    private final IModelMapperService modelMapper;
    private final AnimalRepo animalRepo;


    @Autowired
    public AppointmentService(AppointmentRepo appointmentRepo, IModelMapperService modelMapper, AnimalRepo animalRepo) {
        this.appointmentRepo = appointmentRepo;
        this.modelMapper = modelMapper;
        this.animalRepo = animalRepo;
    }


    @Override
    public AppointmentResponse save(AppointmentSaveRequest appointmentSaveRequest) {


        Animal animal = animalRepo.findById(appointmentSaveRequest.getAnimalId()) // DTO'dan ID'yi alıyoruz
                .orElseThrow(() -> new RuntimeException("Randevu alınacak hayvan bulunamadı."));

        Appointment appointmentToSave = modelMapper.forRequest().map(appointmentSaveRequest, Appointment.class);

        appointmentToSave.setAnimalAppointment(animal);
        appointmentToSave.setId(null);

        Appointment savedAppointment = appointmentRepo.save(appointmentToSave);

        return modelMapper.forResponse().map(savedAppointment, AppointmentResponse.class);
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
