package com.nish.drones.controller;

import com.nish.drones.controller.request.DroneDto;
import com.nish.drones.controller.request.MedicationDto;
import com.nish.drones.repository.model.Drone;
import com.nish.drones.repository.model.Medication;
import com.nish.drones.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MedicationController {

    @Autowired
    MedicationService medicationService;

    @PostMapping("/medication")
    public Medication addMedication(@Valid @RequestBody MedicationDto medicationDto) {
        try {
            Medication createdMedication = medicationService.createdMedication(medicationDto);
            return createdMedication;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @GetMapping("/medications")
    public List<Medication> getAllMedications() {
        return medicationService.getAllMedications();
    }
}
