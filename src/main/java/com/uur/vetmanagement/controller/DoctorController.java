package com.uur.vetmanagement.controller;

import com.uur.vetmanagement.core.result.ResultData;
import com.uur.vetmanagement.dto.request.doctor.DoctorSaveRequest;
import com.uur.vetmanagement.dto.request.doctor.DoctorUpdateRequest;
import com.uur.vetmanagement.dto.response.doctor.DoctorResponse;
import com.uur.vetmanagement.entitiesManager.DoctorManager;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/doctors")
public class DoctorController {

    private final DoctorManager doctorManager;

    public DoctorController(DoctorManager doctorManager) {
        this.doctorManager = doctorManager;
    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<DoctorResponse> save(@Valid @RequestBody DoctorSaveRequest doctorSaveRequest) {
        DoctorResponse response = this.doctorManager.saveDoctor(doctorSaveRequest);
        return ResultData.created(response);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> getById(@PathVariable("id") Long id) {

        DoctorResponse response = this.doctorManager.getDoctorById(id);

        return ResultData.success(response);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Page<DoctorResponse> cursorDoctor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return this.doctorManager.cursorDoctor(page,pageSize);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> update(
            @PathVariable("id") Long id,
            @RequestBody DoctorUpdateRequest doctorUpdateRequest) {

        DoctorResponse response = this.doctorManager.updateDoctor(id,doctorUpdateRequest);
        return ResultData.success(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable("id") Long id) {
        this.doctorManager.delete(id);
        return "İşlem başarılı";
    }
}
