package com.uur.vetmanagement.service.concretes;

import com.uur.vetmanagement.core.config.exception.Msg;
import com.uur.vetmanagement.core.config.exception.NotFoundException;
import com.uur.vetmanagement.core.config.modelMapper.IModelMapperService;
import com.uur.vetmanagement.dto.request.appointment.AppointmentSaveRequest;
import com.uur.vetmanagement.dto.request.appointment.AppointmentUpdateRequest;
import com.uur.vetmanagement.dto.response.appointment.AppointmentResponse;
import com.uur.vetmanagement.entity.Animal;
import com.uur.vetmanagement.entity.Appointment;
import com.uur.vetmanagement.repository.AnimalRepo;
import com.uur.vetmanagement.repository.AppointmentRepo;
import com.uur.vetmanagement.service.abstracts.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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


        Animal animal = animalRepo.findById(appointmentSaveRequest.getAnimalId())
                .orElseThrow(() -> new NotFoundException(Msg.INVALID_ANIMAL));

        Appointment appointmentToSave = this.modelMapper.forRequest().map(appointmentSaveRequest, Appointment.class);

        appointmentToSave.setAnimalAppointment(animal);

        this.appointmentRepo.save(appointmentToSave);

        return this.modelMapper.forResponse().map(appointmentToSave, AppointmentResponse.class);
    }

    @Override
    public AppointmentResponse getById(Long id) {

        return this.modelMapper.forResponse().map(this.appointmentRepo.findById(id),AppointmentResponse.class);
    }

    @Override
    public Page<Appointment> cursor(int page, int pageSize) {

        Pageable pageable = PageRequest.of(page,pageSize);
        return this.appointmentRepo.findAll(pageable);

    }

    @Override
    public AppointmentResponse update(Long id, AppointmentUpdateRequest appointmentUpdateRequest) {
        Appointment existingAppointment = appointmentRepo.findById(id).orElseThrow();
        existingAppointment.setAppointmentDate(appointmentUpdateRequest.getAppointmentDate());
        Appointment save = this.appointmentRepo.save(existingAppointment);
        return this.modelMapper.forResponse().map(save,AppointmentResponse.class);
    }


    @Override
    public void delete(Long id) {
        this.appointmentRepo.deleteById(id);
    }
}
