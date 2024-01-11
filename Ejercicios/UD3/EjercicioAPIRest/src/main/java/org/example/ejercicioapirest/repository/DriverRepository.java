package org.example.ejercicioapirest.repository;

import org.example.ejercicioapirest.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    //Método reconocido por Spring
    Optional<Driver> findByCodeIgnoreCase(String code);
}
