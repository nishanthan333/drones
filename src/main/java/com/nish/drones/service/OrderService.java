package com.nish.drones.service;

import com.nish.drones.controller.request.OrderRequestDto;
import com.nish.drones.repository.model.Delivery;
import com.nish.drones.repository.model.Drone;

public interface OrderService {
    Delivery makeAnOrder(OrderRequestDto orderRequest);

    Drone getDroneForOrder(Long orderId);
}
