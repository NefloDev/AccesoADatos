package org.example.ejercicioapirest.mapper;

import org.example.ejercicioapirest.dto.CircuitDTO;
import org.example.ejercicioapirest.entity.Circuit;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.function.Function;

@Service
public class CircuitDTOMapper implements Function<Circuit, CircuitDTO> {
    @Override
    public CircuitDTO apply(Circuit circuit) {
        return new CircuitDTO(
                circuit.getName(),
                "https://localhost:8080/api/circuits/"+circuit.getCircuitRef()
        );
    }
}
