package org.example.ejercicioapirest.mapper;

import org.example.ejercicioapirest.dto.ConstructorFullDTO;
import org.example.ejercicioapirest.entity.Constructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ConstructorFullDTOMapper implements Function<Constructor, ConstructorFullDTO> {
    @Override
    public ConstructorFullDTO apply(Constructor constructor) {
        return new ConstructorFullDTO(
                constructor.getConstructorId(),
                constructor.getConstructorRef(),
                constructor.getName(),
                constructor.getDrivers().stream().map(d -> "https://localhost:8080/drivers/"+d.getCode()).toList(),
                constructor.getNationality(),
                constructor.getUrl()
        );
    }
}
