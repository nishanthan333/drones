package com.nish.drones.service.impl;

import com.nish.drones.controller.request.OrderRequest;
import com.nish.drones.repository.DeliveryRepository;
import com.nish.drones.repository.DroneRepository;
import com.nish.drones.repository.MedicationRepository;
import com.nish.drones.repository.helper.DroneModelType;
import com.nish.drones.repository.helper.OrderState;
import com.nish.drones.repository.model.Delivery;
import com.nish.drones.repository.model.Drone;
import com.nish.drones.repository.model.DroneResponse;
import com.nish.drones.repository.model.Medication;
import com.nish.drones.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.nish.drones.repository.helper.DroneModelType.*;

public class OrderServiceImpl implements OrderService {

    @Autowired
    DroneRepository droneRepository;

    @Autowired
    MedicationRepository medicationRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    @Override
    public Delivery makeAnOrder(OrderRequest orderRequest) {
        Delivery delivery = new Delivery();
        List<Drone> availableDrones = droneRepository.getAllDronesAvailableForLoading();
        Drone drone = new Drone();
        if (availableDrones.size() > 0) {

            List<DroneModelType> availableDroneTypes = availableDrones
                    .stream()
                    .map(Drone::getModelType)
                    .collect(Collectors.toList());

            DroneModelType requiredModelType = getRequiredModelType(orderRequest);

            delivery.setDeliveryAddress(orderRequest.getAddress());

            try {
                drone = getDrone(availableDrones, requiredModelType);
                delivery.setDrone(drone);
                delivery.setOrderState(OrderState.ACCEPTED);
            } catch (Exception ex) {
                System.out.println("Looks like no available drones found");
                delivery.setOrderState(OrderState.REJECTED);
            }

        }

        return deliveryRepository.save(delivery);
    }

    private Drone getDrone(List<Drone> availableDrones, DroneModelType requiredModelType) throws Exception {
        Drone drone = new Drone();

        List<DroneModelType> availableDroneTypes = availableDrones
                .stream()
                .map(Drone::getModelType)
                .collect(Collectors.toList());

        if (availableDroneTypes.contains(requiredModelType)) {
            drone = availableDrones.get(availableDroneTypes.indexOf(requiredModelType));
        } else {
            throw new Exception("no drones found");
        }

        return drone;
    }

    private DroneModelType getRequiredModelType(OrderRequest orderRequest) {
        DroneModelType requiredDroneTypes = HEAVYWEIGHT;
        List<Medication> medications = orderRequest.getMedications();
        List<Integer> quantity = orderRequest.getQuantities();

        Double parcelWeight = new Double(1);

        if (medications.size() > 0 && quantity.size() > 0 && medications.size() == quantity.size()) {
            if (parcelWeight < 10.0) {

            } else if (parcelWeight < 30.0) {
                requiredDroneTypes = LIGHTWEIGHT;
            } else if(parcelWeight < 100.0) {
                requiredDroneTypes = MIDDLEWEIGHT;
            } else if (parcelWeight > 1000.0) {
                requiredDroneTypes = CRUISERWEIGHT;
            }

        }
        return requiredDroneTypes;
    }


}
