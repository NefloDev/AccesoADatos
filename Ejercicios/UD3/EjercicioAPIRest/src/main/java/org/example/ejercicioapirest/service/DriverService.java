package org.example.ejercicioapirest.service;

import org.example.ejercicioapirest.model.Driver;

import java.util.List;
import java.util.Optional;

public interface DriverService {
    //RETRIEVE
    List<Driver> getAllDrivers();
    Optional<Driver> getDriverByCode(String code);
    //CREATE
    Driver save(Driver driver);
    //DELETE
    void deleteByCode(String code);
}
