package com.uur.vetmanagement.service.concretes;

import com.uur.vetmanagement.core.config.exception.NotFoundException;
import com.uur.vetmanagement.core.config.modelMapper.IModelMapperService;
import com.uur.vetmanagement.entity.Animal;
import com.uur.vetmanagement.repository.AnimalRepo;
import com.uur.vetmanagement.repository.CustomerRepo;
import com.uur.vetmanagement.repository.DoctorRepo;
import com.uur.vetmanagement.service.abstracts.IAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService implements IAnimalService {

    private final AnimalRepo animalRepo;
    private final IModelMapperService modelMapper;
    private final CustomerRepo customerRepo;
    private final DoctorRepo doctorRepo;


    @Autowired
    public AnimalService(AnimalRepo animalRepo, IModelMapperService modelMapper, CustomerRepo customerRepo, DoctorRepo doctorRepo) {
        this.animalRepo = animalRepo;
        this.modelMapper = modelMapper;
        this.customerRepo = customerRepo;
        this.doctorRepo = doctorRepo;
    }


    @Override
    public Animal save(Animal animal) {
        return this.animalRepo.save(animal);
    }

    @Override
    public Animal getById(Long id) {
        return this.animalRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Hayvan ID'si bulunamadı: " + id));
    }

    @Override
    public Page<Animal> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.animalRepo.findAll(pageable);
    }

    @Override
    public Animal update( Animal animal) {
        return this.animalRepo.save(animal);
    }

    @Override
    public void delete(Long id) {

        if (!this.animalRepo.existsById(id)) {
            throw new RuntimeException("Silinmek istenen hayvan ID'si bulunamadı: " + id);
        }

        // 2. 🗑️ Entity'yi sil
        this.animalRepo.deleteById(id);
    }


    public List<Animal> findByName(String name){
        return this.animalRepo.findByNameContainingIgnoreCase(name);
    }

    public List<Animal> findByCustomerId(Long customerId){
        return this.animalRepo.findByAnimalCustomerId(customerId);
    }
}
