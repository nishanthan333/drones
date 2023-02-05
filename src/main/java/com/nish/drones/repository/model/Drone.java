package com.nish.drones.repository.model;

import com.nish.drones.repository.helper.DroneModelType;
import com.nish.drones.repository.helper.DroneState;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "drones")
public class Drone extends AuditModel {

    @Id
    @GeneratedValue(generator = "drone_generator")
    @SequenceGenerator(
            name = "drone_generator",
            sequenceName = "drone_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(columnDefinition = "text", unique = true, nullable = false)
    @Size(max = 100)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private DroneModelType modelType;

    @Column(precision = 10, scale = 2)
    private Double weight;

    @Column(precision = 10, scale = 2)
    private Double carryLimit;

    // battery capacity
    @Column(precision=10, scale=2)
    private Integer batteryPercentage;

    @Enumerated(EnumType.STRING)
    private DroneState droneState = DroneState.IDLE;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public DroneModelType getModelType() {
        return modelType;
    }

    public void setModelType(DroneModelType modelType) {
        this.modelType = modelType;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getCarryLimit() {
        return carryLimit;
    }

    public void setCarryLimit(Double carryLimit) {
        this.carryLimit = carryLimit;
    }

    public Integer getBatteryPercentage() {
        return batteryPercentage;
    }

    public void setBatteryPercentage(Integer batteryPercentage) {
        this.batteryPercentage = batteryPercentage;
    }

    public DroneState getDroneState() {
        return droneState;
    }

    public void setDroneState(DroneState droneState) {
        this.droneState = droneState;
    }
}
