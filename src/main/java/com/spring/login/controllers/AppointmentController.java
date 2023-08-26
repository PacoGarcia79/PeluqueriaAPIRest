package com.spring.login.controllers;

import java.sql.Date;
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

import com.spring.login.models.Appointment;
import com.spring.login.models.Schedule;
import com.spring.login.models.User;
import com.spring.login.security.services.AppointmentService;

@RestController
@RequestMapping(path = "/api/cita", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@CrossOrigin(origins = "*")
public class AppointmentController {

	@Autowired
	AppointmentService citaService;

	/**
	 * Este método se usa para obtener el total de citas
	 * @return
	 */
	@GetMapping("/getCitas")
	@PreAuthorize("hasRole('CLIENTE') or hasRole('EMPLEADO') or hasRole('ADMIN')")
	@ResponseBody
	public ResponseEntity<List<Appointment>> getAllCitas() {
		List<Appointment> citas = citaService.getAllCitas();
		
		if(citas.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return new ResponseEntity<>(citas, HttpStatus.OK);
	}

	/**
	 * Este método se usa para obtener una cita por su id
	 * @param id
	 * @return
	 */
	@GetMapping("/{idCita}")
	@PreAuthorize("hasRole('CLIENTE') or hasRole('EMPLEADO') or hasRole('ADMIN')")
	@ResponseBody
	public ResponseEntity<Appointment> getCitaById(@PathVariable(name = "idCita") Long id) {
		Optional<Appointment> optionalValue = citaService.getCitaById(id);
		
		return optionalValue.isPresent() ? new ResponseEntity<>(optionalValue.get(), HttpStatus.OK) 
				: ResponseEntity.notFound().build();
	}	
}
