package org.example.ejercicioapirest.dto;

public record ResultFullDTO(
        Long id,
        String race,
        String driver,
        int grid,
        int position,
        int points
) {
}
