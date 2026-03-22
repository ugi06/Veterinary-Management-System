package com.uur.vetmanagement.entitiesManager;

import com.uur.vetmanagement.core.config.modelMapper.IModelMapperService;
import com.uur.vetmanagement.dto.request.animal.AnimalSaveRequest;
import com.uur.vetmanagement.dto.request.animal.AnimalUpdateRequest;
import com.uur.vetmanagement.dto.response.animal.AnimalResponse;
import com.uur.vetmanagement.entity.Animal;
import com.uur.vetmanagement.entity.Customer;
import com.uur.vetmanagement.entity.Doctor;
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
    private final DoctorRepo doctorRepo;
    private final AnimalService animalService;
    private final ICustomerService customerService;



    public AnimalManager(IModelMapperService modelMapper, DoctorRepo doctorRepo, AnimalService animalService, ICustomerService customerService) {
        this.modelMapper = modelMapper;
        this.doctorRepo = doctorRepo;
        this.animalService = animalService;
        this.customerService = customerService;
    }

    public AnimalResponse save(AnimalSaveRequest animalSaveRequest) {

        Animal animalToSave = this.modelMapper.forRequest().map(animalSaveRequest, Animal.class);

        Customer owner = this.customerService.getById(animalSaveRequest.getCustomerId());

        animalToSave.setAnimalCustomer(owner);

        if (animalSaveRequest.getDoctorIds() != null && !animalSaveRequest.getDoctorIds().isEmpty()) {
            List<Doctor> doctors = doctorRepo.findAllById(animalSaveRequest.getDoctorIds());
            animalToSave.setAnimalDoctors(doctors);
        }

        Animal savedAnimal = this.animalService.save(animalToSave);

        return this.modelMapper.forResponse().map(savedAnimal, AnimalResponse.class);
    }


    public AnimalResponse getAnimalById(Long id) {

        Animal animal = this.animalService.getById(id);

        return this.modelMapper.forResponse().map(animal, AnimalResponse.class);
    }

    public Page<AnimalResponse> cursor(int page, int pageSize) {

        Page<Animal> animalPage = this.animalService.cursor(page, pageSize);


        return animalPage
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class));
    }

    public AnimalResponse updateAnimal(Long id, AnimalUpdateRequest request) {

        Animal existingAnimal = this.animalService.getById(id);

        if (request.getName() != null) existingAnimal.setName(request.getName());
        if (request.getSpecies() != null) existingAnimal.setSpecies(request.getSpecies());
        if (request.getBreed() != null) existingAnimal.setBreed(request.getBreed());
        if (request.getColour() != null) existingAnimal.setColour(request.getColour());
        if (request.getDateOfBirth() != null) existingAnimal.setDateOfBirth(request.getDateOfBirth());
        if (request.getGender() != null) existingAnimal.setGender(Animal.gender.valueOf(request.getGender()));
        if (request.getCustomerId() != null) {
            Customer newOwner = this.customerService.getById(request.getCustomerId());
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
                existingAnimal.setAnimalDoctors(new ArrayList<>());
            }
        }

        Animal updatedAnimal = this.animalService.update(existingAnimal);

        return this.modelMapper.forResponse().map(updatedAnimal, AnimalResponse.class);
    }

    public List<AnimalResponse> getAnimalsByName(String name) {
        List<Animal> animals = this.animalService.findByName(name);

        return animals.stream()
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class))
                .collect(Collectors.toList());
    }

    public List<AnimalResponse> getAnimalsByCustomerId(Long customerId) {

        this.customerService.getById(customerId);

        List<Animal> animals = this.animalService.findByCustomerId(customerId);

        return animals.stream()
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class))
                .collect(Collectors.toList());
    }

    public String delete(Long id) {
        this.animalService.delete(id);
        return "İşlem başaralı" ;
    }
}
