package com.uur.vetmanagement.controller;

import com.uur.vetmanagement.core.result.ResultData;
import com.uur.vetmanagement.dto.request.animal.AnimalSaveRequest;
import com.uur.vetmanagement.dto.request.animal.AnimalUpdateRequest;
import com.uur.vetmanagement.dto.response.animal.AnimalResponse;
import com.uur.vetmanagement.entitiesManager.AnimalManager;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/animals")
public class AnimalController {

    private final AnimalManager animalManager;


    public AnimalController(AnimalManager animalManager) {
        this.animalManager = animalManager;
    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest animalSaveRequest) {

        AnimalResponse response = this.animalManager.save(animalSaveRequest);

        return ResultData.created(response);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> getById(@PathVariable("id") Long id) {

        AnimalResponse response = this.animalManager.getAnimalById(id);

        return ResultData.success(response);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Page<AnimalResponse> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return this.animalManager.cursor(page, pageSize);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(

            @PathVariable("id") Long id,
            @RequestBody AnimalUpdateRequest animalUpdateRequest // DTO alınıyor
    ) {
        AnimalResponse response = this.animalManager.updateAnimal(id, animalUpdateRequest);

        return ResultData.success(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {

        this.animalManager.delete(id);
    }

    @GetMapping("/by-name")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> getAnimalsByName(@RequestParam String name) {

        List<AnimalResponse> response = this.animalManager.getAnimalsByName(name);

        return ResultData.success(response);
    }

    @GetMapping("/by-customer/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> getAnimalsByCustomerId(@PathVariable("customerId") Long customerId) {

        List<AnimalResponse> response = this.animalManager.getAnimalsByCustomerId(customerId);

        return ResultData.success(response);
    }

}
