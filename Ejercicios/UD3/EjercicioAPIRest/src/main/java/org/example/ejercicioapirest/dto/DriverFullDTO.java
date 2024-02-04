package org.example.ejercicioapirest.dto;

import java.time.LocalDate;

public record DriverFullDTO(
        Long id,
        String code,
        String name,
        LocalDate dob,
        String nationality,
        String constructor,
        String url
) {
}
