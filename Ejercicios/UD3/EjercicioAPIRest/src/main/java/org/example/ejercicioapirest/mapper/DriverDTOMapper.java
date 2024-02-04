package org.example.ejercicioapirest.mapper;

import org.example.ejercicioapirest.dto.DriverDTO;
import org.example.ejercicioapirest.entity.Driver;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.function.Function;

@Service
public class DriverDTOMapper implements Function<Driver, DriverDTO> {
    @Override
    public DriverDTO apply(Driver driver) {
        return new DriverDTO(
                driver.getForename() + " " + driver.getSurname(),
                "http://localhost:8080/api/drivers/"+driver.getCode()
        );
    }
}
