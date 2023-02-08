package com.nish.drones.controller.request;

import com.nish.drones.repository.model.Medication;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class MedicationDto {

    @NotBlank(message = "The name is required.")
    String name;

    @NotNull(message = "The weight is required.")
    Double weight;

    @NotBlank
    String code;

    byte[] image;

    public Medication getMedication() {
        return new Medication(name, weight, code);
    }
}
