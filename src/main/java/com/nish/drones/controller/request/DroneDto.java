package com.nish.drones.controller.request;

import com.nish.drones.repository.helper.DroneModelType;
import com.nish.drones.repository.helper.DroneState;
import com.nish.drones.repository.model.Drone;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class DroneDto {
    @NotBlank
    @Pattern(regexp="[A-Z0-9]+[-_]", message = "only 0-9, A-Z, _, - is allowed")
    String serialNumber;

    @NotNull(message = "model type cannot be empty")
    DroneModelType modelType;

    @Min(value = 1, message = "weight must be between 0-500")
    @Max(value=500, message = "weight must be between 0-500")
    @NotNull(message = "weight cannot be empty")
    BigInteger weight;

    @NotNull(message = "Carry limit is required")
    BigDecimal carryLimit;

    DroneState droneState;

    public Drone toDrone() {
        return new Drone(serialNumber, modelType, weight.intValue(), carryLimit.doubleValue());
    }
}
