package org.api.springf1.controller;

import org.api.springf1.dto.DriverDTO;
import org.api.springf1.dto.DriverResponse;
import org.api.springf1.model.Driver;
import org.api.springf1.repository.DriverRepository;
import org.api.springf1.service.DriverServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DriverRestControllerTest {
    @Mock
    DriverRepository driverRepo;
    @InjectMocks
    DriverServiceImpl driverService;
    Driver driver;
    DriverDTO driverDTO;

    @BeforeEach
    public void setup(){
        driver = Driver.builder().id(2L).code("RCK").forename("RICK").surname("ROLL").build();
        driverDTO = DriverDTO.builder().id(2L).code("RCK").forename("RICK").surname("ROLL").build();
    }

    @Test
    public void driverService_getDriverByCode_returnsDriverDTO(){
        when(driverRepo.findByCodeIgnoreCase(any(String.class))).thenReturn(Optional.ofNullable(driver));

        DriverDTO driverGetTest = driverService.getDriverByCode("RCK");

        assertNotNull(driverGetTest);
        assertEquals("RCK", driverGetTest.code());
        verify(driverRepo, times(1)).findByCodeIgnoreCase("RCK");
    }

    @Test
    public void driverService_updateDriver_returnsDriverDTO(){
        when(driverRepo.save(any(Driver.class))).thenReturn(driver);
        /*Hago el caso de prueba en el que se busca por ID porque en updateDriver() hago una comprobacion
        para ver que el id exista*/
        when(driverRepo.findById(any())).thenReturn(Optional.ofNullable(driver));

        Driver driverToUpdate = driver;
        driverToUpdate.setForename("Alejandro");
        DriverDTO driverUpdateTest = driverService.updateDriver(driverToUpdate);

        assertNotNull(driverUpdateTest);
        assertEquals("Alejandro", driverUpdateTest.forename());
        verify(driverRepo, times(1)).save(driverToUpdate);
    }

    @Test
    public void driverService_deleteDriver_returnsNothing(){
        when(driverRepo.findByCodeIgnoreCase(any())).thenReturn(Optional.ofNullable(driver));

        driverService.deleteDriverByCode("RCK");
        verify(driverRepo, times(1)).delete(driver);
    }

    @Test
    public void driverService_getDrivers_returnsDriverResponse(){
        when(driverRepo.findAll(any(Pageable.class))).thenReturn(new PageImpl(List.of(driver)));

        DriverResponse driversResponse = driverService.getDrivers(0, 10);

        assertEquals(List.of(driverDTO), driversResponse.content());
        verify(driverRepo, times(1)).findAll(PageRequest.of(0, 10));
    }

}