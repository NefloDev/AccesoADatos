package org.example.ejercicioapirest.dto;

import java.util.List;

public record ConstructorFullDTO(
        Long id,
        String constructorRef,
        String name,
        List<String> drivers,
        String nationality,
        String url
) {
}
