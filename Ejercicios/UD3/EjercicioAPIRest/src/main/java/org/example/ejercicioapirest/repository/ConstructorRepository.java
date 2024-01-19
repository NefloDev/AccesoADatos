package org.example.ejercicioapirest.repository;

import org.example.ejercicioapirest.models.Constructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConstructorRepository extends JpaRepository<Constructor, Long> {
    Optional<Constructor> findByConstructorRefIgnoreCase(String ref);
}
