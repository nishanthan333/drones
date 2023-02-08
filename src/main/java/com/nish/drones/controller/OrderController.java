package com.nish.drones.controller;

import com.nish.drones.controller.request.OrderRequestDto;
import com.nish.drones.repository.model.Delivery;
import com.nish.drones.repository.model.Drone;
import com.nish.drones.repository.model.Medication;
import com.nish.drones.service.DroneService;
import com.nish.drones.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    DroneService droneService;

    @GetMapping("/order/{orderId}")
    public Drone getDrone(@PathVariable Long orderId) {
        try {
            return orderService.getDroneForOrder(orderId);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @PostMapping("/order")
    public Delivery placeOrder(@Valid @RequestBody OrderRequestDto orderRequest) {
        Delivery delivery = new Delivery();
        try {
            delivery = orderService.makeAnOrder(orderRequest);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return delivery;
    }

    @GetMapping("/order/drone/{droneId}")
    public List<Medication> getMedicationOrdersByDroneId(@PathVariable Long droneId) {
        List<Medication> medications = new ArrayList<>();
        try {
            medications = droneService.getMedicationsDroneIsCarrying(droneId);

        } catch (Exception ex) {
            System.out.println("Faced an error " + ex.getMessage());
        }
        return medications;

    }

}
