package org.seydaliev.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "device_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "model")
public abstract class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelName;
    private boolean isAvailable;
    private String serialNumber;
    private String color;
    private double size;
    private double price;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;
}

