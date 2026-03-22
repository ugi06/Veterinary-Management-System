package com.uur.vetmanagement.controller;

import com.uur.vetmanagement.entity.Vaccine;
import com.uur.vetmanagement.service.abstracts.IVaccineService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/vaccines")
public class VaccineController {

    private final IVaccineService vaccineService;

    public VaccineController(IVaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Vaccine save(@Valid @RequestBody Vaccine vaccine) {
        return this.vaccineService.save(vaccine);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Vaccine getById(@PathVariable Long id) {
        return this.vaccineService.getById(id);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Vaccine> findAll() {
        return this.vaccineService.findAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Vaccine update(@PathVariable Long id, @RequestBody Vaccine vaccine) {
        return this.vaccineService.update(id, vaccine);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        this.vaccineService.delete(id);
    }

}
