package org.example.ejercicioapirest.service;

import org.example.ejercicioapirest.models.Constructor;
import org.example.ejercicioapirest.models.Driver;
import org.example.ejercicioapirest.models.Result;

import java.util.List;
import java.util.Optional;

public interface RestService {
    List<Driver> getAllDrivers();

    Optional<Driver> getDriverByCode(String code);

    Driver saveDriver(Driver driver);

    void deleteDriverByCode(String code);

    List<Constructor> getAllConstructors();

    Optional<Constructor> getConstructorByRef(String ref);

    Constructor saveConstructor(Constructor constructor);

    void deleteByRef(String ref);

    List<Result> getAllResults();

    Optional<Result> getResultById(Long id);

    Result saveResult(Result result);

    void deleteResultById(Long id);
}
