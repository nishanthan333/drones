package com.nish.drones.repository;

import com.nish.drones.repository.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    @Query(
        value = "SELECT id FROM delivery d WHERE d.drone_id = :droneId ORDER BY d.created_at DESC FETCH FIRST 1 ROWS ONLY",
        nativeQuery = true)
    List<Long> findLastDeliveryMadeByDroneId(Long droneId);




}
