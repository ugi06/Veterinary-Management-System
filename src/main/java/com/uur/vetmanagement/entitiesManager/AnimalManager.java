package com.uur.vetmanagement.entitiesManager;

import com.uur.vetmanagement.core.config.modelMapper.IModelMapperService;
import com.uur.vetmanagement.dto.request.animal.AnimalSaveRequest;
import com.uur.vetmanagement.dto.request.animal.AnimalUpdateRequest;
import com.uur.vetmanagement.dto.response.animal.AnimalResponse;
import com.uur.vetmanagement.entity.Animal;
import com.uur.vetmanagement.entity.Customer;
import com.uur.vetmanagement.entity.Doctor;
import com.uur.vetmanagement.repository.CustomerRepo;
import com.uur.vetmanagement.repository.DoctorRepo;
import com.uur.vetmanagement.service.abstracts.ICustomerService;
import com.uur.vetmanagement.service.concretes.AnimalService;
import org.springframework.data.domain.Page;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AnimalManager {

    private final IModelMapperService modelMapper;
    private final CustomerRepo customerRepo;
    private final DoctorRepo doctorRepo;
    private final AnimalService animalService; // Buraya sadece CRUD Service'i enjekte ediyoruz
    private final ICustomerService customerService;

    // Constructor Injection


    public AnimalManager(IModelMapperService modelMapper, CustomerRepo customerRepo, DoctorRepo doctorRepo, AnimalService animalService, ICustomerService customerService) {
        this.modelMapper = modelMapper;
        this.customerRepo = customerRepo;
        this.doctorRepo = doctorRepo;
        this.animalService = animalService;
        this.customerService = customerService;
    }

    public AnimalResponse save(AnimalSaveRequest animalSaveRequest) {

        // 1. DTO -> Entity dönüşümü
        Animal animalToSave = this.modelMapper.forRequest().map(animalSaveRequest, Animal.class);

        // 2. İlişkili Entity'leri bulma (İŞ MANTIĞI)
        Customer owner = customerRepo.findById(animalSaveRequest.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Müşteri ID'si bulunamadı."));

        // 3. İlişkileri kurma
        animalToSave.setAnimalCustomer(owner);

        if (animalSaveRequest.getDoctorIds() != null && !animalSaveRequest.getDoctorIds().isEmpty()) {
            List<Doctor> doctors = doctorRepo.findAllById(animalSaveRequest.getDoctorIds());
            animalToSave.setAnimalDoctors(doctors);
        }

        // 4. CRUD Service'i çağır
        // Service, sadece Entity'yi alır ve kaydeder.
        Animal savedAnimal = this.animalService.save(animalToSave);

        // 5. Entity -> Response DTO dönüşümü
        return this.modelMapper.forResponse().map(savedAnimal, AnimalResponse.class);
    }


    public AnimalResponse getAnimalById(Long id) {

        Animal animal = this.animalService.getById(id);

        return this.modelMapper.forResponse().map(animal, AnimalResponse.class);
    }

    public Page<AnimalResponse> cursor(int page, int pageSize) {

        // 1. Service'i çağırarak sayfalandırılmış Entity'leri çek
        Page<Animal> animalPage = this.animalService.cursor(page, pageSize);

        // 2. 📌 İŞ MANTIĞI: Entity sayfasını Response DTO sayfasına dönüştürme

        // 3. DTO sayfasını döndür
        return animalPage
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class));
    }

    // Manager Metodu, DTO ve ID alır.
    public AnimalResponse updateAnimal(Long id, AnimalUpdateRequest request) {

        Animal existingAnimal = this.animalService.getById(id);

        if (request.getName() != null) existingAnimal.setName(request.getName());
        if (request.getSpecies() != null) existingAnimal.setSpecies(request.getSpecies());
        if (request.getBreed() != null) existingAnimal.setBreed(request.getBreed());
        if (request.getColour() != null) existingAnimal.setColour(request.getColour());
        if (request.getDateOfBirth() != null) existingAnimal.setDateOfBirth(request.getDateOfBirth());
        if (request.getGender() != null) existingAnimal.setGender(Animal.gender.valueOf(request.getGender()));
        if (request.getCustomerId() != null) {
            Customer newOwner = customerRepo.findById(request.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Müşteri ID'si bulunamadı: " + request.getCustomerId()));
            existingAnimal.setAnimalCustomer(newOwner);
        }
        if (request.getDoctorIds() != null) {

            if (!request.getDoctorIds().isEmpty()) {
                List<Doctor> doctors = doctorRepo.findAllById(request.getDoctorIds());

                if (doctors.size() != request.getDoctorIds().size()) {
                    throw new RuntimeException("Belirtilen doktor ID'lerinden bazıları bulunamadı.");
                }

                existingAnimal.setAnimalDoctors(doctors);
            } else {
                // Eğer liste boş geldiyse, mevcut tüm doktor ilişkilerini kaldır.
                existingAnimal.setAnimalDoctors(new ArrayList<>());
            }
        }

        Animal updatedAnimal = this.animalService.update(existingAnimal);

        return this.modelMapper.forResponse().map(updatedAnimal, AnimalResponse.class);
    }

    public List<AnimalResponse> getAnimalsByName(String name) {
        List<Animal> animals = this.animalService.findByName(name);

        // DTO'ya dönüşüm
        return animals.stream()
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class))
                .collect(Collectors.toList());
    }

    // 2. Hayvanları sahibine göre filtreleme
    public List<AnimalResponse> getAnimalsByCustomerId(Long customerId) {

        // 📌 KRİTİK KONTROL: Customer var mı?
        // ICustomerService içindeki metot, ID bulunamazsa HATA (RuntimeException/NotFoundException) fırlatmalıdır.
        this.customerService.getById(customerId);
        // Hata fırlatılmazsa, müşteri var demektir.

        // Müşteri bulundu, şimdi hayvanlarını çek
        List<Animal> animals = this.animalService.findByCustomerId(customerId);

        // ... (DTO dönüşümü)
        return animals.stream()
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class))
                .collect(Collectors.toList());
    }

}
