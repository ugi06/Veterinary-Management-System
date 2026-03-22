package com.uur.vetmanagement.controller;

import com.uur.vetmanagement.core.result.ResultData;
import com.uur.vetmanagement.dto.request.appointment.AppointmentSaveRequest;
import com.uur.vetmanagement.dto.request.appointment.AppointmentUpdateRequest;
import com.uur.vetmanagement.dto.response.appointment.AppointmentResponse;
import com.uur.vetmanagement.entitiesManager.AppointmentDateManager;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/appointments")
public class AppointmentController {

    private final AppointmentDateManager appointmentManager;

    public AppointmentController(AppointmentDateManager appointmentManager) {
        this.appointmentManager = appointmentManager;
    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> save(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest) {
         return ResultData.success(this.appointmentManager.save(appointmentSaveRequest));

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> getById(@PathVariable Long id) {
        return ResultData.success(this.appointmentManager.getById(id));

    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Page<AppointmentResponse> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return this.appointmentManager.cursor(page, pageSize);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> update(@PathVariable Long id, @RequestBody AppointmentUpdateRequest appointmentUpdateRequest) {
        return ResultData.success(this.appointmentManager.update(id,appointmentUpdateRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable Long id) {
        this.appointmentManager.delete(id);
        return "İşlem başarılı";
    }

}
