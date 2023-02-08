package com.nish.drones.service;

import com.nish.drones.controller.request.MedicationDto;
import com.nish.drones.repository.model.Medication;

import java.util.List;


public interface MedicationService {
    Medication createdMedication(MedicationDto medicationDto);

    List<Medication> getAllMedications();
}
