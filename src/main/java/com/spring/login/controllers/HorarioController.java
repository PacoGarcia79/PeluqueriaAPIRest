package com.spring.login.controllers;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.login.models.Disponibilidad;
import com.spring.login.models.Horario;
import com.spring.login.payload.response.MessageResponse;
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

	@PutMapping("/addNoAvailabilityDates/{fechaComienzo}/{fechaFin}/{empleados}/{horas}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> addNoAvailabilityDates(@PathVariable("fechaComienzo") Date fechaComienzo,
			@PathVariable("fechaFin") Date fechaFinal, @PathVariable("empleados") String empleados,
			@PathVariable("horas") String horarios) {

		int result = horarioService.addNoAvailabilityDates(fechaComienzo, fechaFinal, empleados, horarios);

		return getResponseMessage(result);
	}
	
	@PutMapping("/delNoAvailabilityDates/{fechaComienzo}/{fechaFin}/{empleados}/{horas}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> delNoAvailabilityDates(@PathVariable("fechaComienzo") Date fechaComienzo,
			@PathVariable("fechaFin") Date fechaFinal, @PathVariable("empleados") String empleados,
			@PathVariable("horas") String horarios) {

		int result = horarioService.delNoAvailabilityDates(fechaComienzo, fechaFinal, empleados, horarios);

		return getResponseMessage(result);
	}
	
	@PutMapping("/delNoAvailabilityDatesById/{ids}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> delNoAvailabilityDatesById(@PathVariable("ids") String ids) {

		int result = horarioService.delNoAvailabilityDatesById(ids);

		return getResponseMessage(result);
	}

	private ResponseEntity<MessageResponse> getResponseMessage(int result) {
		switch (result) {
		case 1:
			return new ResponseEntity<>(new MessageResponse("Registro actualizado"), HttpStatus.OK);
		case 2:
			return new ResponseEntity<>(new MessageResponse("Error registro"), HttpStatus.CONFLICT);
		default:
			return new ResponseEntity<>(new MessageResponse("Error al actualizar"), HttpStatus.CONFLICT);
		}
	}
	
	
}
