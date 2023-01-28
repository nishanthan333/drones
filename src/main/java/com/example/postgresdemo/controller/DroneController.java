package com.example.postgresdemo.controller;

import com.example.postgresdemo.model.Drone;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
public class DroneController {


    @GetMapping("/drones")
    public List<String> getAllDrones() {
        return Arrays.asList("test", "array", "return");
    }

    @PostMapping("/drones")
    public String addDrone(@Valid @RequestBody Drone drone) {
        return "Drone created";
    }

}
