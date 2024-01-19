package org.example.ejercicioapirest.controller;

import org.example.ejercicioapirest.models.Constructor;
import org.example.ejercicioapirest.models.Driver;
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
    GET http://localhos:8080/api/drivers
     */
    @GetMapping("/drivers")
    public ResponseEntity<List<Driver>> getAllDrivers() {
        return ResponseEntity.ok(service.getAllDrivers());
    }
    /*
    GET http://localhos:8080/api/drivers/alo
     */
    @GetMapping("/drivers/{code}")
    public ResponseEntity<Driver> getByCode(@PathVariable String code){
        return this.service.getDriverByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    /*
    POST http://localhost:8080/api/drivers
     */
    @PostMapping("/drivers")
    public ResponseEntity<Driver> create(@RequestBody Driver driver){
        if(driver.getDriverId() != null){
            return ResponseEntity.badRequest().build();
        }
        this.service.saveDriver(driver);
        return ResponseEntity.ok(driver);
    }
    /*
    PUT http://localhost:8080/api/drivers
     */
    @PutMapping("/drivers")
    public ResponseEntity<Driver> update(@RequestBody Driver driver){
        this.service.saveDriver(driver);
        return ResponseEntity.ok(driver);
    }
    /*
    DELETE http://localhost:8080/api/drivers/alo
     */
    @DeleteMapping("/drivers/{code}")
    public ResponseEntity<Driver> deleteByCode(@PathVariable String code){
        this.service.deleteDriverByCode(code);
        return ResponseEntity.noContent().build();
    }
    /*
    GET http://localhost:8080/api/drivers
     */
    @GetMapping("/constructors")
    public ResponseEntity<List<Constructor>> getAllConstructors() {
        return ResponseEntity.ok(service.getAllConstructors());
    }
    /*
    GET http://localhost:8080/api/drivers/alo
     */
    @GetMapping("/constructors/{constructorRef}")
    public ResponseEntity<Constructor> getByConstructorRef(@PathVariable String constructorRef){
        return this.service.getConstructorByRef(constructorRef)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    /*
    POST http://localhost:8080/api/drivers
     */
    @PostMapping("/constructors")
    public ResponseEntity<Constructor> create(@RequestBody Constructor constructor){
        if(constructor.getConstructorId() != null){
            return ResponseEntity.badRequest().build();
        }
        this.service.saveConstructor(constructor);
        return ResponseEntity.ok(constructor);
    }
    /*
    PUT http://localhost:8080/api/drivers
     */
    @PutMapping("/constructors")
    public ResponseEntity<Constructor> update(@RequestBody Constructor constructor){
        this.service.saveConstructor(constructor);
        return ResponseEntity.ok(constructor);
    }
    /*
    DELETE http://localhost:8080/api/drivers/alo
     */
    @DeleteMapping("/constructors/{constructorRef}")
    public ResponseEntity<Constructor> deleteByRef(@PathVariable String constructorRef){
        this.service.deleteByRef(constructorRef);
        return ResponseEntity.noContent().build();
    }
}
