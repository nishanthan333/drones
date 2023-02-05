package com.nish.drones.service;

import com.nish.drones.controller.request.OrderRequest;
import com.nish.drones.repository.model.Delivery;

public interface OrderService {
    Delivery makeAnOrder(OrderRequest orderRequest);
}
