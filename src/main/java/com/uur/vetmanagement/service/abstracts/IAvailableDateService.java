package com.uur.vetmanagement.service.abstracts;


import com.uur.vetmanagement.dto.request.availableDate.AvailableDateSaveRequest;
import com.uur.vetmanagement.dto.response.availableDate.AvailableDateResponse;
import com.uur.vetmanagement.entity.AvailableDate;

import java.util.List;

public interface IAvailableDateService {

    AvailableDateResponse save (AvailableDateSaveRequest availableDateSaveRequest);

    AvailableDate getById (Long id);

    List<AvailableDate> findAll();

    AvailableDate update (Long id, AvailableDate availableDate);

    void delete(Long id);
}
