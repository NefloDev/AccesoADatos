package org.example.ejercicioapirest.mapper;

import org.example.ejercicioapirest.dto.ResultFullDTO;
import org.example.ejercicioapirest.entity.Result;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ResultFullDTOMapper implements Function<Result, ResultFullDTO> {
    @Override
    public ResultFullDTO apply(Result result) {
        return new ResultFullDTO(
                result.getResultId(),
                "https://localhost:8080/api/races/" + result.getRace().getRaceId(),
                "https://localhost:8080/api/drivers/" + result.getDriver().getCode(),
                result.getGrid(),
                result.getPosition(),
                result.getPoints()
        );
    }
}
