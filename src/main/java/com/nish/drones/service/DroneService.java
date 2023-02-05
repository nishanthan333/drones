package com.nish.drones.service;

import com.nish.drones.repository.model.Medication;

import java.util.List;

public interface DroneService {
    List<Medication> getMedicationsDroneIsCarrying(Long droneId);
}
