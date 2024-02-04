package org.example.ejercicioapirest.dto;

import java.sql.Time;
import java.time.LocalDate;

public record RaceFullDTO(
        Long id,
        int year,
        int round,
        String circuit,
        String name,
        LocalDate date,
        Time time,
        String url
) {
}
