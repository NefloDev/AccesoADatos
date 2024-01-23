package org.example.ejercicioapirest.controller;

import org.example.ejercicioapirest.models.Constructor;
import org.example.ejercicioapirest.models.Driver;
import org.example.ejercicioapirest.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.example.ejercicioapirest.models.Result;

@RestController
@RequestMapping("/api")
public class ApiRestController {
    private final RestService service;

    @Autowired
    public ApiRestController(RestService service) {
        this.service = service;
    }

    /*
     * GET http://localhos:8080/api/drivers
     */
    @GetMapping("/drivers")
    public ResponseEntity<List<Driver>> getAllDrivers() {
        return ResponseEntity.ok(service.getAllDrivers());
    }

    /*
     * GET http://localhos:8080/api/drivers/alo
     */
    @GetMapping("/drivers/{code}")
    public ResponseEntity<Driver> getByCode(@PathVariable("code") String code) {
        return this.service.getDriverByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*
     * POST http://localhost:8080/api/drivers
     */
    @PostMapping("/drivers")
    public ResponseEntity<Driver> create(@RequestBody Driver driver) {
        if (driver.getDriverId() != null) {
            return ResponseEntity.badRequest().build();
        }
        this.service.saveDriver(driver);
        return ResponseEntity.ok(driver);
    }

    /*
     * PUT http://localhost:8080/api/drivers
     */
    @PutMapping("/drivers")
    public ResponseEntity<Driver> update(@RequestBody Driver driver) {
        this.service.saveDriver(driver);
        return ResponseEntity.ok(driver);
    }

    /*
     * DELETE http://localhost:8080/api/drivers/alo
     */
    @DeleteMapping("/drivers/{code}")
    public ResponseEntity<Driver> deleteByCode(@PathVariable("code") String code) {
        this.service.deleteDriverByCode(code);
        return ResponseEntity.noContent().build();
    }

    /*
     * GET http://localhost:8080/api/constructors
     */
    @GetMapping("/constructors")
    public ResponseEntity<List<Constructor>> getAllConstructors() {
        return ResponseEntity.ok(service.getAllConstructors());
    }

    /*
     * GET http://localhost:8080/api/constructors/mclaren
     */
    @GetMapping("/constructors/{constructorRef}")
    public ResponseEntity<Constructor> getByConstructorRef(@PathVariable("constructorRef") String constructorRef) {
        return this.service.getConstructorByRef(constructorRef)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*
     * POST http://localhost:8080/api/constructors
     */
    @PostMapping("/constructors")
    public ResponseEntity<Constructor> create(@RequestBody Constructor constructor) {
        if (constructor.getConstructorId() != null) {
            return ResponseEntity.badRequest().build();
        }
        this.service.saveConstructor(constructor);
        return ResponseEntity.ok(constructor);
    }

    /*
     * PUT http://localhost:8080/api/constructors
     */
    @PutMapping("/constructors")
    public ResponseEntity<Constructor> update(@RequestBody Constructor constructor) {
        this.service.saveConstructor(constructor);
        return ResponseEntity.ok(constructor);
    }

    /*
     * DELETE http://localhost:8080/api/constructors/mclaren
     */
    @DeleteMapping("/constructors/{constructorRef}")
    public ResponseEntity<Constructor> deleteByRef(@PathVariable("constructorRef") String constructorRef) {
        this.service.deleteByRef(constructorRef);
        return ResponseEntity.noContent().build();
    }

    /*
     * GET http://localhost:8080/api/results
     */
    @GetMapping("/results")
    public ResponseEntity<List<Result>> getAllResults() {
        return ResponseEntity.ok(service.getAllResults());
    }

    /*
     * GET http://localhost:8080/api/results/2
     */
    @GetMapping("/results/{resultId}")
    public ResponseEntity<Result> getByResultId(@PathVariable("resultId") Long id) {
        return this.service.getResultById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*
     * POST http://localhost:8080/api/results
     */
    @PostMapping("/results")
    public ResponseEntity<Result> create(@RequestBody Result result) {
        if (result.getResultId() != null) {
            return ResponseEntity.badRequest().build();
        }
        this.service.saveResult(result);
        return ResponseEntity.ok(result);
    }

    /*
     * PUT http://localhost:8080/api/results
     */
    @PutMapping("/results")
    public ResponseEntity<Result> update(@RequestBody Result result) {
        this.service.saveResult(result);
        return ResponseEntity.ok(result);
    }

    /*
     * DELETE http://localhost:8080/api/results/2
     */
    @DeleteMapping("/results/{resultId}")
    public ResponseEntity<Result> deleteResultById(@PathVariable("resultId") Long resultId) {
        this.service.deleteResultById(resultId);
        return ResponseEntity.noContent().build();
    }
}
