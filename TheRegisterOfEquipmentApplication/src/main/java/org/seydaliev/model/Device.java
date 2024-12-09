package org.seydaliev.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String manufacturerCountry;
    private String manufacturer;
    private boolean onlineOrderAvailable;
    private boolean installmentAvailable;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    private List<Model> models;
}
