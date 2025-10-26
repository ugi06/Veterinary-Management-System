package com.uur.vetmanagement.entitiesManager;

import com.uur.vetmanagement.core.config.modelMapper.IModelMapperService;
import com.uur.vetmanagement.dto.request.doctor.DoctorSaveRequest;
import com.uur.vetmanagement.dto.request.doctor.DoctorUpdateRequest;
import com.uur.vetmanagement.dto.response.doctor.DoctorResponse;
import com.uur.vetmanagement.entity.Doctor;
import com.uur.vetmanagement.service.abstracts.IDoctorService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorManager {
    private final IDoctorService doctorService;
    private final IModelMapperService modelMapper;


    public DoctorManager(IDoctorService doctorService, IModelMapperService modelMapper) {
        this.doctorService = doctorService;
        this.modelMapper = modelMapper;
    }

    public DoctorResponse saveDoctor(DoctorSaveRequest doctorSaveRequest){
        Doctor doctor = modelMapper.forRequest().map(doctorSaveRequest, Doctor.class);
        Doctor savedDoctor = this.doctorService.save(doctor);
        return this.modelMapper.forResponse().map(savedDoctor,DoctorResponse.class);
    }

    public DoctorResponse getDoctorById(Long id){
        Doctor doctor = this.doctorService.getById(id);
        return this.modelMapper.forResponse().map(doctor,DoctorResponse.class);
    }

    public Page<DoctorResponse> cursorDoctor(int page, int pageSize){
        Page<Doctor> doctorPage = this.doctorService.cursor(page,pageSize);
        return doctorPage.map(doctor ->
                this.modelMapper.forResponse().map(doctor,DoctorResponse.class));
    }

    public  DoctorResponse updateDoctor(Long id , DoctorUpdateRequest doctorUpdateRequest){
        Doctor doctor = this.modelMapper.forRequest().map(doctorUpdateRequest,Doctor.class);
        Doctor savedDoctor = this.doctorService.update(id,doctor);
        return this.modelMapper.forResponse().map(savedDoctor,DoctorResponse.class);
    }

    public void deleteDoctor(Long id){
        this.doctorService.delete(id);
    }

}
