package org.example.ejercicioapirest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resultid")
    Long resultId;
    @ManyToOne
    @JoinColumn(name = "raceid")
    Race race;
    @ManyToOne
    @JoinColumn(name = "driverid")
    Driver driver;

    Integer grid;
    Integer position;
    Integer points;
}
