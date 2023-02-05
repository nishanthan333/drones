package com.nish.drones.controller;

import com.nish.drones.repository.DroneRepository;
import com.nish.drones.repository.model.Drone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DroneController {

    @Autowired
    DroneRepository droneRepository;

    @GetMapping("/drones")
    public List<Drone> getAllDrones() {
        return droneRepository.findAll();
    }

    @PostMapping("/drone")
    public Drone addDrone(@Valid @RequestBody Drone drone) {
        try {
            Drone createdDrone = droneRepository.save(drone);
            return createdDrone;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @GetMapping("/drone/{drone_id}")
    public Drone getDrone(@PathVariable Long drone_id) {
        try {
            Drone foundDrone = droneRepository.findById(drone_id).get();
            return foundDrone;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @GetMapping("/drone/available")
    public List<Drone> getDronesAvailableForLoading() {
        List<Drone> availableDrones = new ArrayList<>();
        try {
            availableDrones = droneRepository.getAllDronesAvailableForLoading();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return availableDrones;
    }

}
