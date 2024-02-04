package org.example.ejercicioapirest.mapper;

import org.example.ejercicioapirest.dto.RaceDTO;
import org.example.ejercicioapirest.entity.Race;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.function.Function;

@Service
public class RaceDTOMapper implements Function<Race, RaceDTO> {
    @Override
    public RaceDTO apply(Race race) {
        return new RaceDTO(
                race.getName(),
                "http://localhost:8080/api/races/" + race.getRaceId()
        );
    }
}
