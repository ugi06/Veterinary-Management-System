package com.uur.vetmanagement.entitiesManager;

import com.uur.vetmanagement.core.config.modelMapper.IModelMapperService;
import com.uur.vetmanagement.dto.request.vaccination.VaccinationSaveRequest;
import com.uur.vetmanagement.dto.request.vaccination.VaccinationUpdateRequest;
import com.uur.vetmanagement.dto.response.vaccination.VaccinationResponse;
import com.uur.vetmanagement.entity.Vaccination;
import com.uur.vetmanagement.service.abstracts.IVaccinationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class VaccinationManager {
    private final IVaccinationService vaccinationService;
    private final IModelMapperService modelMapper;

    public VaccinationManager(IVaccinationService vaccinationService, IModelMapperService modelMapper) {
        this.vaccinationService = vaccinationService;
        this.modelMapper = modelMapper;
    }

    // --- CREATE ---
    public VaccinationResponse saveVaccination(VaccinationSaveRequest request) {
        // DTO -> Entity dönüşümü
        Vaccination vaccination = this.modelMapper.forRequest().map(request, Vaccination.class);

        // Service katmanına Entity'yi kaydet
        Vaccination savedVaccination = this.vaccinationService.save(vaccination);

        // Entity -> Response DTO dönüşümü
        return this.modelMapper.forResponse().map(savedVaccination, VaccinationResponse.class);
    }

    // --- READ / FIND BY ID ---
    public VaccinationResponse getVaccinationById(Long id) {
        // Service'ten Entity'yi çek (Bulunamazsa NotFoundException fırlatılır)
        Vaccination vaccination = this.vaccinationService.getById(id);

        // Entity -> Response DTO dönüşümü
        return this.modelMapper.forResponse().map(vaccination, VaccinationResponse.class);
    }

    // --- READ / FIND ALL (PAGINATION) ---
    public Page<VaccinationResponse> getAllVaccinations(Pageable pageable) {
        // Service'ten sayfalanmış Entity listesini çek
        Page<Vaccination> vaccinationPage = this.vaccinationService.findAll(pageable);

        // Page<Entity> -> Page<Response DTO> dönüşümü
        return vaccinationPage.map(vaccination ->
                this.modelMapper.forResponse().map(vaccination, VaccinationResponse.class));
    }

    // --- UPDATE ---
    public VaccinationResponse updateVaccination(Long id, VaccinationUpdateRequest request) {
        // 1. Request DTO -> Entity dönüşümü
        Vaccination vaccinationToUpdate = this.modelMapper.forRequest().map(request, Vaccination.class);

        // 2. Service katmanında güncelleme yap (ID kontrolü Service'de yapılmalı)
        Vaccination updatedVaccination = this.vaccinationService.update(id, vaccinationToUpdate);

        // 3. Entity -> Response DTO dönüşümü
        return this.modelMapper.forResponse().map(updatedVaccination, VaccinationResponse.class);
    }

    // --- DELETE ---
    public void deleteVaccination(Long id) {
        // Silme işlemi Service katmanına devredilir (Varlık kontrolü Service'de yapılmalı)
        this.vaccinationService.delete(id);
    }
    public List<VaccinationResponse> getVaccinationsByAnimalId(Long animalId) {
        List<Vaccination> vaccinations = this.vaccinationService.findByAnimalId(animalId);

        // Entity List -> Response DTO List dönüşümü
        return vaccinations.stream()
                .map(vaccination -> this.modelMapper.forResponse().map(vaccination, VaccinationResponse.class))
                .collect(Collectors.toList());
    }

    // 3. İSTER: Tarih aralığına göre listeleme (Yaklaşan koruyuculuk bitiş tarihi)
    public List<VaccinationResponse> getVaccinationsByProtectionPeriod(LocalDate startDate, LocalDate endDate) {
        List<Vaccination> vaccinations = this.vaccinationService.findByProtectionPeriod(startDate, endDate);

        // Entity List -> Response DTO List dönüşümü
        return vaccinations.stream()
                .map(vaccination -> this.modelMapper.forResponse().map(vaccination, VaccinationResponse.class))
                .collect(Collectors.toList());
    }
}
