package com.nish.drones.service.impl;

import com.nish.drones.controller.request.DroneDto;
import com.nish.drones.repository.DeliveryRepository;
import com.nish.drones.repository.DroneRepository;
import com.nish.drones.repository.MedicationOrderRepository;
import com.nish.drones.repository.model.Drone;
import com.nish.drones.repository.model.Medication;
import com.nish.drones.repository.model.MedicationOrder;
import com.nish.drones.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DroneServiceImpl implements DroneService {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    DroneRepository droneRepository;

    @Autowired
    MedicationOrderRepository medicationOrderRepository;

    @Override
    public List<Drone> getAllDrones() {
        return droneRepository.findAll();
    }

    @Override
    public List<Medication> getMedicationsDroneIsCarrying(Long droneId) {
        List<Medication> medicationOrders = new ArrayList<>();
        List<Long> delivery = deliveryRepository.findLastDeliveryMadeByDroneId(droneId);

        if (delivery.size() > 0) {
            Long deliveryId = delivery.get(0);
            medicationOrders = medicationOrderRepository.findByDeliveryId(deliveryId).stream().map(MedicationOrder::getMedication).collect(Collectors.toList());

        }

        return medicationOrders;
    }

    @Override
    public Drone createDrone(DroneDto drone) {
        return droneRepository.save(drone.toDrone());
    }

    @Override
    public Drone getDrone(Long drone_id) {
        if (droneRepository.existsById(drone_id)) {
            return droneRepository.findById(drone_id).get();
        }
        return null;
    }

    @Override
    public List<Drone> getDronesAvailableForLoading() {
        return droneRepository.getAllDronesAvailableForLoading();
    }
}
