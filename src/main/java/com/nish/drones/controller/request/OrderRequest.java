package com.nish.drones.controller.request;

import com.nish.drones.repository.model.Medication;

import java.util.List;

public class OrderRequest {
    List<Medication> medications;
    List<Integer> quantities;
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
