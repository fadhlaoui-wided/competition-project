package com.competition.repositories;

import com.competition.entities.Bateaus;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BateausRepository  extends JpaRepository<Bateaus, Integer> {

    Bateaus findByNom(String name);
}