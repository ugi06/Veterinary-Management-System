package com.uur.vetmanagement.service.concretes;

import com.uur.vetmanagement.core.config.exception.NotFoundException;
import com.uur.vetmanagement.entity.Vaccination;
import com.uur.vetmanagement.entity.Vaccine;
import com.uur.vetmanagement.repository.VaccinationRepo;
import com.uur.vetmanagement.service.abstracts.IVaccinationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VaccinationService implements IVaccinationService {

    private final VaccinationRepo vaccinationRepo;
    // ... (Diğer bağımlılıklar varsa)

    public VaccinationService(VaccinationRepo vaccinationRepo) {
        this.vaccinationRepo = vaccinationRepo;
    }

    @Override
    public Vaccination save(Vaccination vaccination) {

        // 1. İSTER KONTROLÜ: Aynı tip ve aktif aşı kontrolü
        // Animal ID'sini ve aşı kodunu kontrol ederek aktif aşı var mı bakar
        boolean activeVaccinationExists = this.vaccinationRepo
                .findFirstByAnimalIdAndVaccineTypeCodeAndProtectionEndDateGreaterThan(
                        vaccination.getAnimal().getId(),
                        // Vaccine Entity'sinden 'code' alanına erişim
                        vaccination.getVaccineType().getCode(),
                        LocalDate.now()
                ).isPresent();

        if (activeVaccinationExists) {
            throw new RuntimeException("Bu hayvanda, koruma süresi bitmemiş aynı tip aşı zaten mevcut.");
        }

        // 2. KRİTİK HESAPLAMA: protectionEndDate'i hesapla
        // VaccineType'ta durationInDays alanının bulunduğunu varsayıyoruz.
        Vaccine vaccineType = vaccination.getVaccineType(); // Zaten yukarıda yüklendiğini varsayarsak

        if (vaccineType.getDurationInDays() > 0) {
            // protectionEndDate = vaccinationDate + durationInDays
            LocalDate endDate = vaccination.getVaccinationDate()
                    .plusDays(vaccineType.getDurationInDays());

            vaccination.setProtectionEndDate(endDate);
        } else {
            // Koruma süresi yoksa, isteğe bağlı olarak hata fırlatılabilir veya null ayarlanabilir.
            // Entity'de nullable=false olduğu için bir değer atanmalıdır.
            // Burası için iş kuralınızı netleştirmelisiniz. Şimdilik başlangıç tarihini atayalım.
            vaccination.setProtectionEndDate(vaccination.getVaccinationDate());
        }

        return this.vaccinationRepo.save(vaccination);
    }

    // CRUD Metotları (Daha önce düzeltilenler)
    @Override
    public Vaccination getById(Long id) {
        return this.vaccinationRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Aşı ID'si bulunamadı: " + id));
    }

    // Uyumsuzluk Giderildi: Pageable tipi kullanıldı.
    @Override
    public Page<Vaccination> findAll(Pageable pageable) {
        return this.vaccinationRepo.findAll(pageable);
    }

    // Uyumsuzluk Giderildi: ID parametresi ve varlık kontrolü eklendi.
    @Override
    public Vaccination update(Long id, Vaccination vaccination) {
        this.vaccinationRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Güncellenecek aşı ID'si bulunamadı: " + id));

        vaccination.setId(id);
        return this.vaccinationRepo.save(vaccination);
    }

    @Override
    public void delete(Long id) {
        if (!this.vaccinationRepo.existsById(id)){
            throw new NotFoundException("Silinmek istenen aşı ID'si bulunamadı: " + id);
        }
        this.vaccinationRepo.deleteById(id);
    }

    // --- YENİ İŞLEVSELLİKLERİN IMPLEMENTASYONU ---

    // 2. İSTER: Hayvan ID'sine göre listeleme
    @Override
    public List<Vaccination> findByAnimalId(Long animalId) {
        return this.vaccinationRepo.findByAnimalId(animalId);
    }

    // 3. İSTER: Tarih aralığına göre listeleme
    @Override
    public List<Vaccination> findByProtectionPeriod(LocalDate startDate, LocalDate endDate) {
        return this.vaccinationRepo.findByProtectionEndDateBetween(startDate, endDate);
    }
}
