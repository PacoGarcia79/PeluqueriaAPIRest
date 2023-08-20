package com.spring.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.login.models.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {

}
