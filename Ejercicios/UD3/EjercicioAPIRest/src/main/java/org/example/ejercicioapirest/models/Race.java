package org.example.ejercicioapirest.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Table(name = "races")
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "raceid")
    private Long raceId;

    private int year;
    private int round;

    @OneToOne
    @JoinColumn(name = "circuitid")
    private Circuit circuit;

    private String name;
    private LocalDate date;
    private Time time;
    private String url;
}
