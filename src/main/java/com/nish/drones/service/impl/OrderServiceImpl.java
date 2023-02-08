package com.nish.drones.service.impl;

import com.nish.drones.controller.request.OrderRequestDto;
import com.nish.drones.repository.DeliveryRepository;
import com.nish.drones.repository.DroneRepository;
import com.nish.drones.repository.MedicationRepository;
import com.nish.drones.repository.helper.DroneModelType;
import com.nish.drones.repository.helper.OrderState;
import com.nish.drones.repository.model.Delivery;
import com.nish.drones.repository.model.Drone;
import com.nish.drones.repository.model.Medication;
import com.nish.drones.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.nish.drones.repository.helper.DroneModelType.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    DroneRepository droneRepository;

    @Autowired
    MedicationRepository medicationRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    @Override
    public Delivery makeAnOrder(OrderRequestDto orderRequest) {
        Delivery delivery = new Delivery();
        List<Drone> availableDrones = droneRepository.getAllDronesAvailableForLoading();
        Drone drone = new Drone();
        if (availableDrones.size() > 0) {

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

    @Override
    public Drone getDroneForOrder(Long orderId) {
        if (deliveryRepository.existsById(orderId)) {
            Delivery delivery = deliveryRepository.findById(orderId).get();
            return delivery.getDrone();
        }
        return null;
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

    private DroneModelType getRequiredModelType(OrderRequestDto orderRequest) {
        DroneModelType requiredDroneTypes = LIGHTWEIGHT;
        List<Medication> medications = orderRequest.getMedications();
        List<Integer> quantity = orderRequest.getQuantities();

        Double parcelWeight = 0.00;

        if (medications.size() > 0 && quantity.size() > 0 && medications.size() == quantity.size()) {

            for (int i = 0; i < medications.size(); i++) {
                parcelWeight += medications.get(i).getWeight() * quantity.get(i);
            }

            if (parcelWeight < 10.0) {
                requiredDroneTypes = LIGHTWEIGHT;
            } else if (parcelWeight < 30.0) {
                requiredDroneTypes = MIDDLEWEIGHT;
            } else if(parcelWeight < 100.0) {
                requiredDroneTypes = CRUISERWEIGHT;
            } else if (parcelWeight > 1000.0) {
                requiredDroneTypes = HEAVYWEIGHT;
            }
        }
        return requiredDroneTypes;
    }


}
