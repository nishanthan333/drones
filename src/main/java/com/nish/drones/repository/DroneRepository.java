package com.nish.drones.repository;

import com.nish.drones.repository.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {

    @Query(value = "SELECT * FROM drone d WHERE d.drone_state = 'IDLE' AND d.",
            nativeQuery = true)
    List<Drone> getAllDronesAvailableForLoading();



}
