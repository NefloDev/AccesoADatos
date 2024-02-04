package org.example.ejercicioapirest.mapper;

import org.example.ejercicioapirest.dto.DriverFullDTO;
import org.example.ejercicioapirest.entity.Driver;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DriverFullDTOMapper implements Function<Driver, DriverFullDTO> {
    @Override
    public DriverFullDTO apply(Driver driver) {
        return new DriverFullDTO(
                driver.getDriverId(),
                driver.getCode(),
                driver.getForename() + driver.getSurname(),
                driver.getDob(),
                driver.getNationality(),
                "https://localhost:8080/api/constructors/" + driver.getConstructor().getConstructorRef(),
                driver.getUrl()
        );
    }
}
