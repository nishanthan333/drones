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

}
