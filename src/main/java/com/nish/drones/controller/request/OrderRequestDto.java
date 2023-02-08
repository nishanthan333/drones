package com.nish.drones.controller.request;

import com.nish.drones.repository.model.Medication;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderRequestDto {

    @NotNull(message = "The medications are required.")
    List<@Valid Medication> medications;

    @NotNull(message = "The quantities are required.")
    List<Integer> quantities;

    @NotBlank(message = "The address is required.")
    String address;

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }

    public void setQuantities(List<Integer> quantities) {
        this.quantities = quantities;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
