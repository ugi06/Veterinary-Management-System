package com.uur.vetmanagement.controller;

import com.uur.vetmanagement.dto.request.availableDate.AvailableDateSaveRequest;
import com.uur.vetmanagement.dto.response.availableDate.AvailableDateResponse;
import com.uur.vetmanagement.entity.AvailableDate;
import com.uur.vetmanagement.service.abstracts.IAvailableDateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/availableDates")
public class AvailableDateController {

    private final IAvailableDateService availableDateService;

    public AvailableDateController(IAvailableDateService availableDateService) {
        this.availableDateService = availableDateService;
    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public AvailableDateResponse save(@Valid @RequestBody AvailableDateSaveRequest availableDateSaveRequest) {
        return this.availableDateService.save(availableDateSaveRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AvailableDate getById(@PathVariable Long id) {
        return this.availableDateService.getById(id);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<AvailableDate> findAll() {
        return this.availableDateService.findAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AvailableDate update(@PathVariable Long id, @RequestBody AvailableDate availableDate) {
        return this.availableDateService.update(id, availableDate);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        this.availableDateService.delete(id);
    }
}
