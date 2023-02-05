package com.nish.drones.service.impl;

import com.nish.drones.repository.DeliveryRepository;
import com.nish.drones.repository.MedicationOrderRepository;
import com.nish.drones.repository.model.Medication;
import com.nish.drones.repository.model.MedicationOrder;
import com.nish.drones.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DroneServiceImpl implements DroneService {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    MedicationOrderRepository medicationOrderRepository;

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
}
