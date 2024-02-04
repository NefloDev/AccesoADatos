package org.example.ejercicioapirest.repository;

import org.example.ejercicioapirest.entity.Circuit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CircuitRepository extends JpaRepository<Circuit, Long> {
    Optional<Circuit> findCircuitByCircuitRefIgnoreCase(String ref);
}
