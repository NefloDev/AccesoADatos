package org.example.ejercicioapirest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "circuits")
public class Circuit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "circuitid")
    private Long circuitId;

    @Column(name = "circuitref", unique = true)
    private String circuitRef;

    @Column(unique = true)
    private String name;

    private String location;
    private String country;
    private double lat;
    private double lng;
    private int alt;
    private String url;

}
