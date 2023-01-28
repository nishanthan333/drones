package com.example.postgresdemo.model;

import javax.persistence.*;

@Entity
@Table(name = "drones")
public class Drone {

    @Id
    @GeneratedValue(generator = "drone_generator")
    @SequenceGenerator(
            name = "drone_generator",
            sequenceName = "drone_sequence",
            initialValue = 1000
    )
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
