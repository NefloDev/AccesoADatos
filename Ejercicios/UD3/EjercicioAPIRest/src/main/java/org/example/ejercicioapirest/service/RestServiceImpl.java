package org.example.ejercicioapirest.service;

import org.example.ejercicioapirest.models.Constructor;
import org.example.ejercicioapirest.models.Driver;
import org.example.ejercicioapirest.repository.ConstructorRepository;
import org.example.ejercicioapirest.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestServiceImpl implements RestService {
    private final DriverRepository drivRepo;
    private final ConstructorRepository constRepo;
    @Autowired
    public RestServiceImpl(DriverRepository drivRepo, ConstructorRepository constRepo) {
        this.drivRepo = drivRepo;
        this.constRepo = constRepo;
    }
    @Override
    public List<Driver> getAllDrivers() {
        return drivRepo.findAll();
    }
    @Override
    public List<Constructor> getAllConstructors() {
        return constRepo.findAll();
    }
    @Override
    public Optional<Driver> getDriverByCode(String code) {
        return drivRepo.findByCodeIgnoreCase(code);
    }
    @Override
    public Optional<Constructor> getConstructorByRef(String ref) {
        return constRepo.findByConstructorRefIgnoreCase(ref);
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
    public void deleteDriverByCode(String code) {
        drivRepo.delete(drivRepo.findByCodeIgnoreCase(code).get());
    }
    @Override
    public void deleteByRef(String ref) {constRepo.delete(constRepo.findByConstructorRefIgnoreCase(ref).get());}
}
