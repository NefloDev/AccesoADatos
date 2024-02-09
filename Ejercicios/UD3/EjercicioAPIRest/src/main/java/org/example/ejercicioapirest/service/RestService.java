package org.example.ejercicioapirest.service;

import org.example.ejercicioapirest.dto.*;
import org.example.ejercicioapirest.entity.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface RestService {
    /*
        GET
     */
    List<DriverDTO> getAllDrivers();
    Page<DriverDTO> getAllDriversProjected(int page, int size, String sortBy, String sortDirection);
    List<ConstructorDTO> getAllConstructors();
    List<ResultDTO> getAllResults();
    List<RaceDTO> getAllRaces();
    List<CircuitDTO> getAllCircuits();
    Optional<DriverFullDTO> getDriverByCode(String code);
    Optional<ConstructorFullDTO> getConstructorByRef(String ref);
    Optional<ResultFullDTO> getResultById(Long id);
    Optional<RaceFullDTO> getRaceById(Long id);
    Optional<Circuit> getCircuitByCircuitRef(String ref);
    /*
        POST
     */
    Driver saveDriver(Driver driver);
    Constructor saveConstructor(Constructor constructor);
    Result saveResult(Result result);
    Race saveRace(Race race);
    Circuit saveCircuit(Circuit circuit);
    /*
        DELETE
     */
    void deleteDriverByCode(String code);
    void deleteConstructorByRef(String ref);
    void deleteResultById(Long id);
    void deleteRaceById(Long id);
    void deleteCircuitByRef(String ref);
}
