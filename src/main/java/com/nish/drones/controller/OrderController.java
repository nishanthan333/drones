package com.nish.drones.controller;

import com.nish.drones.controller.request.OrderRequest;
import com.nish.drones.repository.DeliveryRepository;
import com.nish.drones.repository.MedicationOrderRepository;
import com.nish.drones.repository.model.Delivery;
import com.nish.drones.repository.model.Drone;
import com.nish.drones.repository.model.Medication;
import com.nish.drones.repository.model.MedicationOrder;
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
    DeliveryRepository deliveryRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    DroneService droneService;

    @Autowired
    MedicationOrderRepository medicationOrderRepository;



    @GetMapping("/order/{orderId}")
    public String getDrone(@PathVariable Long orderId) {
        try {
            return "foundDrone";
        } catch (Exception ex) {
            throw ex;
        }
    }

    @PostMapping("/order")
    public Delivery placeOrder(@Valid @RequestBody OrderRequest orderRequest) {
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
