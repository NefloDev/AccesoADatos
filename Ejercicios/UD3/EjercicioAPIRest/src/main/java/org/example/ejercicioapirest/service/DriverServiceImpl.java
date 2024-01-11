package org.example.ejercicioapirest.service;

import org.example.ejercicioapirest.model.Driver;
import org.example.ejercicioapirest.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {
    private final DriverRepository repo;
    @Autowired
    public DriverServiceImpl(DriverRepository repo) {
        this.repo = repo;
    }
    @Override
    public List<Driver> getAllDrivers() {
        return repo.findAll();
    }
    @Override
    public Optional<Driver> getDriverByCode(String code) {
        return repo.findByCodeIgnoreCase(code);
    }
    @Override
    public Driver save(Driver driver) {
        return repo.save(driver);
    }
    @Override
    public void deleteByCode(String code) {
        repo.delete(repo.findByCodeIgnoreCase(code).get());
    }
}
