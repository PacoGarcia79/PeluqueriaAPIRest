package com.spring.peluqueria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.peluqueria.models.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	
}
