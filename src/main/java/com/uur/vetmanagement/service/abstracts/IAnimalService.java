package com.uur.vetmanagement.service.abstracts;

import com.uur.vetmanagement.entity.Animal;
import org.springframework.data.domain.Page;


public interface IAnimalService {


    Animal save(Animal  animal);

    Animal getById (Long id);

    Page<Animal> cursor(int page, int pageSize);

    Animal update (Animal animal);

    void delete(Long id);

}
