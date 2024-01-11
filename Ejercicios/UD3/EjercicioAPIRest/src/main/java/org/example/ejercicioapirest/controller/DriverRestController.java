package org.example.ejercicioapirest.controller;

import org.example.ejercicioapirest.model.Driver;
import org.example.ejercicioapirest.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DriverRestController {
    private final DriverService service;
    @Autowired
    public DriverRestController(DriverService service) {
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
        this.service.save(driver);
        return ResponseEntity.ok(driver);
    }
    /*
    PUT http://localhost:8080/api/drivers
     */
    @PutMapping("/drivers")
    public ResponseEntity<Driver> update(@RequestBody Driver driver){
        this.service.save(driver);
        return ResponseEntity.ok(driver);
    }
    /*
    DELETE http://localhost:8080/api/drivers/alo
     */
    @DeleteMapping("/drivers/{code}")
    public ResponseEntity<Driver> deleteByCode(@PathVariable String code){
        this.service.deleteByCode(code);
        return ResponseEntity.noContent().build();
    }

}
