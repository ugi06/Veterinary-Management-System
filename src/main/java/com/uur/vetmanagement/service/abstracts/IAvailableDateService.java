package com.uur.vetmanagement.service.abstracts;


import com.uur.vetmanagement.entity.AvailableDate;

import java.util.List;

public interface IAvailableDateService {

    AvailableDate save (AvailableDate availableDate);

    AvailableDate getById (Long id);

    List<AvailableDate> findAll();

    AvailableDate update (Long id, AvailableDate availableDate);

    void delete(Long id);
}
