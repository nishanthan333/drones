package com.nish.drones.controller.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class MedicationDto {

    @NotBlank(message = "The name is required.")
    private String name;

    @NotBlank(message = "The weight is required.")
    private Double weight;

    @NotBlank(message = "The code is required.")
    @Pattern(regexp = "^[A-Z]\\d{1,5}$", flags = { Pattern.Flag.CASE_INSENSITIVE, Pattern.Flag.MULTILINE }, message = "The code is invalid.")
    private String code;

    private byte[] image;
}
