package org.example.ejercicioapirest.service;

import org.example.ejercicioapirest.model.Driver;
import org.example.ejercicioapirest.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
