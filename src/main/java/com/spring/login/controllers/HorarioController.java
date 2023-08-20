package com.spring.login.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.login.models.Disponibilidad;
import com.spring.login.models.Horario;
import com.spring.login.repository.HorarioRepository;
import com.spring.login.security.services.HorarioService;

@RestController
@RequestMapping(path = "/api/horario", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@CrossOrigin(origins = "*")
public class HorarioController {
	
	@Autowired
	private HorarioService horarioService;

	@GetMapping("/getHorarios")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	@ResponseBody
	public ResponseEntity<List<Horario>> getHorarios() {
		List<Horario> horarios = horarioService.getHorarios();

		return horarios.isEmpty() ? ResponseEntity.noContent().build() : new ResponseEntity<>(horarios, HttpStatus.OK);
	}

	@GetMapping("/noAvailabilityDates")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	@ResponseBody
	public ResponseEntity<List<Disponibilidad>> findNoAvailabilityDates() throws SQLException {
		
		List<Disponibilidad> noAvailabilityDates = horarioService.findNoAvailabilityDates();
		
		return noAvailabilityDates.isEmpty() ? ResponseEntity.noContent().build()
				: new ResponseEntity<>(noAvailabilityDates, HttpStatus.OK);
	}
}
