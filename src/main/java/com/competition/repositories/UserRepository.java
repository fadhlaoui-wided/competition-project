package com.competition.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.competition.entities.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{

	public List<Users> findByEmail(String email);
}
