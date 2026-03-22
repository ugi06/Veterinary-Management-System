package com.uur.vetmanagement.controller;


import com.uur.vetmanagement.core.result.ResultData;
import com.uur.vetmanagement.dto.request.vaccination.VaccinationSaveRequest;
import com.uur.vetmanagement.dto.request.vaccination.VaccinationUpdateRequest;
import com.uur.vetmanagement.dto.response.vaccination.VaccinationResponse;
import com.uur.vetmanagement.entitiesManager.VaccinationManager;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/vaccinations")
public class VaccinationController {


    private final VaccinationManager vaccinationManager;

    public VaccinationController(VaccinationManager vaccinationManager) {
        this.vaccinationManager = vaccinationManager;
    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // HTTP 201
    public ResultData<VaccinationResponse> save(@Valid @RequestBody VaccinationSaveRequest request) {
        VaccinationResponse response = this.vaccinationManager.saveVaccination(request);
        return ResultData.created(response);
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK) // HTTP 200
    public ResultData<VaccinationResponse> getById(@PathVariable("id") Long id) {
        VaccinationResponse response = this.vaccinationManager.getVaccinationById(id);
        return ResultData.success(response);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK) // HTTP 200
    public ResultData<Page<VaccinationResponse>> findAll(Pageable pageable) {
        Page<VaccinationResponse> response = this.vaccinationManager.getAllVaccinations(pageable);
        return ResultData.success(response);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK) // HTTP 200
    public ResultData<VaccinationResponse> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody VaccinationUpdateRequest request) {

        VaccinationResponse response = this.vaccinationManager.updateVaccination(id, request);
        return ResultData.success(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResultData<String> delete(@PathVariable("id") Long id) {
        this.vaccinationManager.deleteVaccination(id);
        return ResultData.success("İşlem başarılı");
    }

    @GetMapping("/by-animal/{animalId}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccinationResponse>> getByAnimalId(@PathVariable("animalId") Long animalId) {
        List<VaccinationResponse> response = this.vaccinationManager.getVaccinationsByAnimalId(animalId);
        return ResultData.success(response);
    }

    @GetMapping("/expiration-range")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccinationResponse>> getByProtectionPeriod(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<VaccinationResponse> response = this.vaccinationManager.getVaccinationsByProtectionPeriod(startDate, endDate);
        return ResultData.success(response);
    }
}