package com.uur.vetmanagement.controller;


import com.uur.vetmanagement.core.result.Result;
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


    private final VaccinationManager vaccinationManager; // Manager'ı kullan

    public VaccinationController(VaccinationManager vaccinationManager) {
        this.vaccinationManager = vaccinationManager;
    }

    // 1. CREATE (POST)
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // HTTP 201
    public ResultData<VaccinationResponse> save(@Valid @RequestBody VaccinationSaveRequest request) {
        VaccinationResponse response = this.vaccinationManager.saveVaccination(request);
        return ResultData.created(response);
    }

    // 2. READ / FIND BY ID (GET)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK) // HTTP 200
    public ResultData<VaccinationResponse> getById(@PathVariable("id") Long id) {
        VaccinationResponse response = this.vaccinationManager.getVaccinationById(id);
        return ResultData.success(response);
    }

    // 3. READ / FIND ALL (GET) - Sayfalama
    @GetMapping
    @ResponseStatus(HttpStatus.OK) // HTTP 200
    public ResultData<Page<VaccinationResponse>> findAll(Pageable pageable) {
        Page<VaccinationResponse> response = this.vaccinationManager.getAllVaccinations(pageable);
        return ResultData.success(response);
    }

    // 4. UPDATE (PUT)
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK) // HTTP 200
    public ResultData<VaccinationResponse> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody VaccinationUpdateRequest request) {

        VaccinationResponse response = this.vaccinationManager.updateVaccination(id, request);
        return ResultData.success(response);
    }

    // 5. DELETE (DELETE)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // HTTP 204
    public Result delete(@PathVariable("id") Long id) {
        this.vaccinationManager.deleteVaccination(id);
        // Silme başarılı, 204 döner.
        return new Result(true, "Aşı kaydı başarıyla silindi", "204");
    }

    @GetMapping("/by-animal/{animalId}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccinationResponse>> getByAnimalId(@PathVariable("animalId") Long animalId) {
        List<VaccinationResponse> response = this.vaccinationManager.getVaccinationsByAnimalId(animalId);
        return ResultData.success(response);
    }

    // 3. İSTER: Bitiş tarihi aralığına göre aşıları listeleme (Query Param kullanılır)
    @GetMapping("/expiration-range")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccinationResponse>> getByProtectionPeriod(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<VaccinationResponse> response = this.vaccinationManager.getVaccinationsByProtectionPeriod(startDate, endDate);
        return ResultData.success(response);
    }
}