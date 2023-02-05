package com.nish.drones.repository.model;

import javax.persistence.*;

@Entity
@Table(name = "medication_order")
public class MedicationOrder {

    @Id
    @GeneratedValue(generator = "medication_order_generator")
    @SequenceGenerator(
            name = "medication_order_generator",
            sequenceName = "medication_order_sequence",
            initialValue = 1000
    )
    private Long id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medication_id", referencedColumnName = "id")
    private Medication medication;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id", referencedColumnName = "id")
    private Delivery delivery;

    private Integer quantity;

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
