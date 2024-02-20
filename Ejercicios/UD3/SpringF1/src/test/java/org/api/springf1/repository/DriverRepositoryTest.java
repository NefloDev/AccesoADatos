package org.api.springf1.repository;

import org.api.springf1.model.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class DriverRepositoryTest {

    @Autowired
    DriverRepository driverRepo;

    Driver driver;
    Driver driver2;

    @BeforeEach
    void initialize(){
        driver = Driver
                .builder().code("NEB").forename("Alejandro").surname("Nebot")
                .constructor(null).dob(LocalDate.of(2024, 1, 27))
                .nationality("Spanish").build();
        driver2 = Driver
                .builder().code("URE").forename("Antonio").surname("Ureña")
                .constructor(null).dob(LocalDate.of(1945, 6, 1))
                .nationality("Austrian").build();
    }

    @Test
    void driverRepository_findAll_returnsMultipleDrivers(){
        driverRepo.save(driver);
        driverRepo.save(driver2);

        assertEquals(2, driverRepo.findAll().size());
    }

    @Test
    void driverRepository_findByCodeIgnoreCase_returnsNotNull(){
        driverRepo.save(driver);

        assertNotNull(driverRepo.findByCodeIgnoreCase("ALE"));
    }

    @Test
    void driverRepository_save_updateDriver_returnsNotNull(){
        driverRepo.save(driver);
        driver.setForename("Carlos");

        assertNotNull(driverRepo.save(driver));
    }

    @Test
    void driverRepository_delete_returnsNull(){
        driverRepo.save(driver);
        driverRepo.delete(driver);

        assertEquals(0, driverRepo.findAll().size());
    }

}
