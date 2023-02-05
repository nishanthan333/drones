package com.nish.drones.repository.model;

import com.nish.drones.repository.helper.DroneModelType;
import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Entity
@Table(name = "delivery")
@TypeDefs({
        @TypeDef(name = "string-array", typeClass = StringArrayType.class),
        @TypeDef(name = "int-array", typeClass = IntArrayType.class),
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class Delivery {

    @Id
    @GeneratedValue(generator = "delivery_generator")
    @SequenceGenerator(
            name = "delivery_generator",
            sequenceName = "delivery_sequence",
            initialValue = 10
    )
    private Long id;

    @Column(columnDefinition = "VARCHAR(40)", nullable = false)
    private String deliveryAddress;

    @Column(columnDefinition = "VARCHAR(40)")
    private String orderDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "drone_id", referencedColumnName = "id")
    private Drone drone;

}
