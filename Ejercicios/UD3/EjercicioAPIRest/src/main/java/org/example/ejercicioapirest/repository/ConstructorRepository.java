package org.example.ejercicioapirest.repository;

import org.example.ejercicioapirest.entity.Constructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConstructorRepository extends JpaRepository<Constructor, Long> {
    Optional<Constructor> findConstructorByConstructorRefIgnoreCase(String ref);
}
