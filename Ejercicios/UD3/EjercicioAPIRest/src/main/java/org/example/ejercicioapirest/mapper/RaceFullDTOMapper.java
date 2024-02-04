package org.example.ejercicioapirest.mapper;

import org.example.ejercicioapirest.dto.RaceFullDTO;
import org.example.ejercicioapirest.entity.Race;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RaceFullDTOMapper implements Function<Race, RaceFullDTO> {
    @Override
    public RaceFullDTO apply(Race race) {
        return new RaceFullDTO(
                race.getRaceId(),
                race.getYear(),
                race.getRound(),
                "https://localhost:8080/api/circuits/" + race.getCircuit().getCircuitRef(),
                race.getName(),
                race.getDate(),
                race.getTime(),
                race.getUrl()
        );
    }
}
