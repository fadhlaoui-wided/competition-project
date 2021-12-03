package com.competition.repositories;

import com.competition.entities.Personnes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonnesRepository extends JpaRepository<Personnes, Integer> {

    Personnes findByNom(String name);
}
