package org.example.ejercicioapirest.controller;

import org.example.ejercicioapirest.model.Driver;
import org.example.ejercicioapirest.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DriverRestController {
    private final DriverService service;

    @Autowired
    public DriverRestController(DriverService service) {
        this.service = service;
    }

    @GetMapping("/drivers")
    public ResponseEntity<List<Driver>> getAllDrivers() {
        return ResponseEntity.ok(service.getAllDrivers());
    }

}
