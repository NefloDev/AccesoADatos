package org.example.ejercicioapirest.repository;

import org.example.ejercicioapirest.entity.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {
    Optional<Race> findRaceByRaceId(Long id);
}
