package org.example.ejercicioapirest.mapper;

import org.example.ejercicioapirest.dto.ResultDTO;
import org.example.ejercicioapirest.entity.Result;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.function.Function;

@Service
public class ResultDTOMapper implements Function<Result, ResultDTO> {
    @Override
    public ResultDTO apply(Result result) {
        return new ResultDTO(
                result.getResultId(),
                "http://localhost:8080/api/results/"+result.getResultId()
        );
    }
}
