package org.example.ejercicioapirest.mapper;

import org.example.ejercicioapirest.dto.ConstructorDTO;
import org.example.ejercicioapirest.entity.Constructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.function.Function;

@Service
public class ConstructorDTOMapper implements Function<Constructor, ConstructorDTO> {
    @Override
    public ConstructorDTO apply(Constructor constructor) {
        return new ConstructorDTO(
                constructor.getName(),
                "http://localhost:8080/api/constructors/"+constructor.getConstructorRef()
        );
    }
}
