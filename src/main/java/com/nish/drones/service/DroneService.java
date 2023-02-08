package com.nish.drones.service;

import com.nish.drones.controller.request.DroneDto;
import com.nish.drones.repository.model.Drone;
import com.nish.drones.repository.model.Medication;

import java.util.List;

public interface DroneService {
    List<Drone> getAllDrones();
    List<Medication> getMedicationsDroneIsCarrying(Long droneId);
    Drone createDrone(DroneDto drone);
    Drone getDrone(Long drone_id);
    List<Drone> getDronesAvailableForLoading();
}
