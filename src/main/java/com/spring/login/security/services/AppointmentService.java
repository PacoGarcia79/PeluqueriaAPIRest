package com.spring.login.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.login.models.Appointment;
import com.spring.login.repository.AppointmentRepository;

@Service
public class AppointmentService {

	@Autowired
	AppointmentRepository citaRepository;

	public List<Appointment> getAllCitas() {
		return citaRepository.findAll();
	}

	public Optional<Appointment> getCitaById(Long idCita) {
		return citaRepository.findById(idCita);
	}	

}
