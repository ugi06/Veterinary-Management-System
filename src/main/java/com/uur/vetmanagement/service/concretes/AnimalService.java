package com.uur.vetmanagement.service.concretes;

import com.uur.vetmanagement.entity.Animal;
import com.uur.vetmanagement.repository.AnimalRepo;
import com.uur.vetmanagement.service.abstracts.IAnimalService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AnimalService implements IAnimalService {
    private final AnimalRepo animalRepo;

    @Autowired
    public AnimalService(AnimalRepo animalRepo) {
        this.animalRepo = animalRepo;
    }


    @Override
    public Animal save(Animal animal) {
        return this.animalRepo.save(animal);
    }

    @Override
    public Animal getById(Long id) {
        return this.animalRepo.findById(id).orElseThrow();
    }

    @Override
    public List<Animal> findAll() {
        return this.animalRepo.findAll();
    }

    @Override
    public Animal update(Long id, Animal animal) {
        Animal existingAnimal = animalRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal not found with id: " + id));

        existingAnimal.setName(animal.getName());
        existingAnimal.setSpecies(animal.getSpecies());
        existingAnimal.setBreed(animal.isBreed());
        existingAnimal.setGender(animal.getGender());
        existingAnimal.setColour(animal.getColour());
        existingAnimal.setDateOfBirth(animal.getDateOfBirth());
        return  animalRepo.save(existingAnimal);
    }

    @Override
    public void delete(Long id) {
        this.animalRepo.deleteById(id);
    }
}
