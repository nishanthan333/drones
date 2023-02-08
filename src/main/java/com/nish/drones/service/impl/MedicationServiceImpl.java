package com.nish.drones.service.impl;

import com.nish.drones.controller.request.MedicationDto;
import com.nish.drones.repository.MedicationRepository;
import com.nish.drones.repository.model.Medication;
import com.nish.drones.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationServiceImpl implements MedicationService {

    @Autowired
    MedicationRepository medicationRepository;

    @Override
    public Medication createdMedication(MedicationDto medicationDto) {
        Medication medication = medicationRepository.save(medicationDto.getMedication());
        return medication;
    }

    @Override
    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }
}
