package com.uur.vetmanagement.service.abstracts;

import com.uur.vetmanagement.entity.Animal;

import java.util.List;

public interface IAnimalService {

    Animal save (Animal animal);

    Animal getById (Long id);

    List<Animal> findAll();

    Animal update (Long id, Animal animal);

    void delete(Long id);

}
