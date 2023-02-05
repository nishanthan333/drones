package com.nish.drones.repository.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "medication")
public class Medication {

    @Id
    @GeneratedValue(generator = "medication_generator")
    @SequenceGenerator(
            name = "medication_generator",
            sequenceName = "medication_sequence",
            initialValue = 100
    )
    private Long id;

    @Column(columnDefinition = "VARCHAR(40)")
    @NotBlank
    private String name;

    @Column(precision=10, scale=2)
    private Double weight;

    @Column(columnDefinition = "text")
    @NotBlank
    private String code;

    @Column(columnDefinition = "text")
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
