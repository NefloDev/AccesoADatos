package org.example.ejercicioapirest.service;

import org.example.ejercicioapirest.dto.*;
import org.example.ejercicioapirest.entity.*;
import org.example.ejercicioapirest.mapper.*;
import org.example.ejercicioapirest.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestServiceImpl implements RestService {
    private final DriverRepository drivRepo;
    private final DriverDTOMapper driverDTOMapper;
    private final DriverFullDTOMapper driverFullDTOMapper;
    private final ConstructorRepository constRepo;
    private final ConstructorDTOMapper constructorDTOMapper;
    private final ConstructorFullDTOMapper constructorFullDTOMapper;
    private final ResultRepository resRepo;
    private final ResultDTOMapper resultDTOMapper;
    private final ResultFullDTOMapper resultFullDTOMapper;
    private final RaceRepository raceRepo;
    private final RaceDTOMapper raceDTOMapper;
    private final RaceFullDTOMapper raceFullDTOMapper;
    private final CircuitRepository circuitRepo;
    private final CircuitDTOMapper circuitDTOMapper;

    @Autowired
    public RestServiceImpl(DriverRepository drivRepo, DriverFullDTOMapper driverFullDTOMapper, ConstructorRepository constRepo,
                           ResultRepository resRepo, DriverDTOMapper driverDTOMapper,
                           ConstructorDTOMapper constructorDTOMapper, ConstructorFullDTOMapper constructorFullDTOMapper, ResultDTOMapper resultDTOMapper, ResultFullDTOMapper resultFullDTOMapper, RaceRepository raceRepo, RaceDTOMapper raceDTOMapper, RaceFullDTOMapper raceFullDTOMapper, CircuitRepository circuitRepo, CircuitDTOMapper circuitDTOMapper) {
        this.drivRepo = drivRepo;
        this.driverFullDTOMapper = driverFullDTOMapper;
        this.constRepo = constRepo;
        this.resRepo = resRepo;
        this.driverDTOMapper = driverDTOMapper;
        this.constructorDTOMapper = constructorDTOMapper;
        this.constructorFullDTOMapper = constructorFullDTOMapper;
        this.resultDTOMapper = resultDTOMapper;
        this.resultFullDTOMapper = resultFullDTOMapper;
        this.raceRepo = raceRepo;
        this.raceDTOMapper = raceDTOMapper;
        this.raceFullDTOMapper = raceFullDTOMapper;
        this.circuitRepo = circuitRepo;
        this.circuitDTOMapper = circuitDTOMapper;
    }

    @Override
    public List<DriverDTO> getAllDrivers() {
        return drivRepo.findAll().stream().map(driverDTOMapper).toList();
    }
    @Override
    public List<ConstructorDTO> getAllConstructors() {
        return constRepo.findAll().stream().map(constructorDTOMapper).toList();
    }
    @Override
    public List<ResultDTO> getAllResults() {
        return resRepo.findAll().stream().map(resultDTOMapper).toList();
    }
    @Override
    public List<RaceDTO> getAllRaces() {
        return raceRepo.findAll().stream().map(raceDTOMapper).toList();
    }
    @Override
    public List<CircuitDTO> getAllCircuits() {
        return circuitRepo.findAll().stream().map(circuitDTOMapper).toList();
    }

    @Override
    public Optional<DriverFullDTO> getDriverByCode(String code) {
        return drivRepo.findDriverByCodeIgnoreCase(code).map(driverFullDTOMapper);
    }
    @Override
    public Optional<ConstructorFullDTO> getConstructorByRef(String ref) {
        return constRepo.findConstructorByConstructorRefIgnoreCase(ref).map(constructorFullDTOMapper);
    }
    @Override
    public Optional<ResultFullDTO> getResultById(Long id) {
        return resRepo.findById(id).map(resultFullDTOMapper);
    }
    @Override
    public Optional<RaceFullDTO> getRaceById(Long id) {
        return raceRepo.findRaceByRaceId(id).map(raceFullDTOMapper);
    }
    @Override
    public Optional<Circuit> getCircuitByCircuitRef(String ref) {
        return circuitRepo.findCircuitByCircuitRefIgnoreCase(ref);
    }

    @Override
    public Driver saveDriver(Driver driver) {
        return drivRepo.save(driver);
    }
    @Override
    public Constructor saveConstructor(Constructor constructor) {
        return constRepo.save(constructor);
    }
    @Override
    public Result saveResult(Result result) {
        return resRepo.save(result);
    }
    @Override
    public Race saveRace(Race race) {
        return raceRepo.save(race);
    }
    @Override
    public Circuit saveCircuit(Circuit circuit) {
        return circuitRepo.save(circuit);
    }

    @Override
    public void deleteDriverByCode(String code) {
        drivRepo.delete(drivRepo.findDriverByCodeIgnoreCase(code).get());
    }
    @Override
    public void deleteConstructorByRef(String ref) {
        constRepo.delete(constRepo.findConstructorByConstructorRefIgnoreCase(ref).get());
    }
    @Override
    public void deleteResultById(Long id) {
        resRepo.deleteById(id);
    }
    @Override
    public void deleteRaceById(Long id) {
        raceRepo.deleteById(id);
    }
    @Override
    public void deleteCircuitByRef(String ref) {
        circuitRepo.delete(circuitRepo.findCircuitByCircuitRefIgnoreCase(ref).get());
    }
}
