package com.nish.drones.controller;

import com.nish.drones.controller.request.DroneDto;
import com.nish.drones.repository.DroneRepository;
import com.nish.drones.repository.model.Drone;
import com.nish.drones.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DroneController {

    @Autowired
    DroneService droneService;

    @GetMapping("/drones")
    public List<Drone> getAllDrones() {
        return droneService.getAllDrones();
    }

    @PostMapping("/drone")
    public Drone addDrone(@Valid @RequestBody DroneDto drone) {
        try {
            Drone createdDrone = droneService.createDrone(drone);
            return createdDrone;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @GetMapping("/drone/{drone_id}")
    public Drone getDrone(@PathVariable Long drone_id) {
        try {
            Drone foundDrone = droneService.getDrone(drone_id);
            return foundDrone;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @GetMapping("/drone/available")
    public List<Drone> getDronesAvailableForLoading() {
        List<Drone> availableDrones = new ArrayList<>();
        try {
            availableDrones = droneService.getDronesAvailableForLoading();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return availableDrones;
    }

}
