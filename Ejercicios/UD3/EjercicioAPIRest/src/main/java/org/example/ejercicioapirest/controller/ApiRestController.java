package org.example.ejercicioapirest.controller;

import org.example.ejercicioapirest.dto.*;
import org.example.ejercicioapirest.entity.*;
import org.example.ejercicioapirest.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiRestController {
    private final RestService service;

    @Autowired
    public ApiRestController(RestService service) {
        this.service = service;
    }

    /*
     * GET http://localhost:8080/api/drivers
     */
    @GetMapping("/drivers")
    public ResponseEntity<List<DriverDTO>> getAllDrivers() {
        return ResponseEntity.ok(service.getAllDrivers());
    }

    /*
     * GET http://localhost:8080/api/drivers/alo
     */
    @GetMapping("/drivers/{code}")
    public ResponseEntity<DriverFullDTO> getByCode(@PathVariable("code") String code) {
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
    public ResponseEntity<List<ConstructorDTO>> getAllConstructors() {
        return ResponseEntity.ok(service.getAllConstructors());
    }

    /*
     * GET http://localhost:8080/api/constructors/mclaren
     */
    @GetMapping("/constructors/{constructorRef}")
    public ResponseEntity<ConstructorFullDTO> getByConstructorRef(@PathVariable("constructorRef") String constructorRef) {
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
        this.service.deleteConstructorByRef(constructorRef);
        return ResponseEntity.noContent().build();
    }

    /*
     * GET http://localhost:8080/api/results
     */
    @GetMapping("/results")
    public ResponseEntity<List<ResultDTO>> getAllResults() {
        return ResponseEntity.ok(service.getAllResults());
    }

    /*
     * GET http://localhost:8080/api/results/2
     */
    @GetMapping("/results/{resultId}")
    public ResponseEntity<ResultFullDTO> getResultById(@PathVariable("resultId") Long id) {
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

    /*
     * GET http://localhost:8080/api/races
     */
    @GetMapping("/races")
    public ResponseEntity<List<RaceDTO>> getAllRaces() {
        return ResponseEntity.ok(service.getAllRaces());
    }

    /*
     * GET http://localhost:8080/api/races/2
     */
    @GetMapping("/races/{raceId}")
    public ResponseEntity<RaceFullDTO> getRaceById(@PathVariable("raceId") Long id) {
        return this.service.getRaceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*
     * POST http://localhost:8080/api/races
     */
    @PostMapping("/races")
    public ResponseEntity<Race> createRace(@RequestBody Race race) {
        if (race.getRaceId() != null) {
            return ResponseEntity.badRequest().build();
        }
        this.service.saveRace(race);
        return ResponseEntity.ok(race);
    }

    /*
     * PUT http://localhost:8080/api/races
     */
    @PutMapping("/races")
    public ResponseEntity<Race> update(@RequestBody Race race) {
        this.service.saveRace(race);
        return ResponseEntity.ok(race);
    }

    /*
     * DELETE http://localhost:8080/api/races/2
     */
    @DeleteMapping("/races/{raceId}")
    public ResponseEntity<Race> deleteRaceById(@PathVariable("raceId") Long raceId) {
        this.service.deleteRaceById(raceId);
        return ResponseEntity.noContent().build();
    }

    /*
     * GET http://localhost:8080/api/circuits
     */
    @GetMapping("/circuits")
    public ResponseEntity<List<CircuitDTO>> getAllCircuits() {
        return ResponseEntity.ok(service.getAllCircuits());
    }

    /*
     * GET http://localhost:8080/api/circuits/sepang
     */
    @GetMapping("/circuits/{circuitRef}")
    public ResponseEntity<Circuit> getRaceById(@PathVariable("circuitRef") String ref) {
        return this.service.getCircuitByCircuitRef(ref)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*
     * POST http://localhost:8080/api/circuits
     */
    @PostMapping("/circuits")
    public ResponseEntity<Circuit> createCircuit(@RequestBody Circuit circuit) {
        if (circuit.getCircuitId() != null) {
            return ResponseEntity.badRequest().build();
        }
        this.service.saveCircuit(circuit);
        return ResponseEntity.ok(circuit);
    }

    /*
     * PUT http://localhost:8080/api/circuits
     */
    @PutMapping("/circuits")
    public ResponseEntity<Circuit> update(@RequestBody Circuit circuit) {
        this.service.saveCircuit(circuit);
        return ResponseEntity.ok(circuit);
    }

    /*
     * DELETE http://localhost:8080/api/circuits/sepang
     */
    @DeleteMapping("/circuits/{circuitRef}")
    public ResponseEntity<Circuit> deleteCircuitByRef(@PathVariable("raceId") String ref) {
        this.service.deleteCircuitByRef(ref);
        return ResponseEntity.noContent().build();
    }
}
