package com.spring.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.login.models.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer>{

}

