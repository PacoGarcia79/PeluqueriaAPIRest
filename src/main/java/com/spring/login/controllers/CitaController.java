package com.spring.login.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.login.models.Cita;
import com.spring.login.repository.CitaRepository;

@RestController
@RequestMapping(path = "/api/cita", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@CrossOrigin(origins = "*")
public class CitaController {

	@Autowired
	CitaRepository citaRepository;

	@GetMapping("/getCitas")
	@PreAuthorize("hasRole('CLIENTE') or hasRole('EMPLEADO') or hasRole('ADMIN')")
	@ResponseBody
	public ResponseEntity<List<Cita>> getAllCitas() {
		List<Cita> citas = citaRepository.findAll();
		
		if(citas.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return new ResponseEntity<>(citas, HttpStatus.OK);
	}

	@GetMapping("/getCitas/{idCita}")
	@PreAuthorize("hasRole('CLIENTE') or hasRole('EMPLEADO') or hasRole('ADMIN')")
	@ResponseBody
	public ResponseEntity<Cita> getCitaById(@PathVariable(name = "idCita") Long id) {
		Optional<Cita> optionalValue = citaRepository.findById(id);
		
		return optionalValue.isPresent() ? new ResponseEntity<>(optionalValue.get(), HttpStatus.OK) 
				: ResponseEntity.notFound().build();
	}
}
