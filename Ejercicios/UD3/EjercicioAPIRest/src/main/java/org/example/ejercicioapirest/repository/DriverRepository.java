package org.example.ejercicioapirest.repository;

import org.example.ejercicioapirest.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findDriverByCodeIgnoreCase(String code);
}
