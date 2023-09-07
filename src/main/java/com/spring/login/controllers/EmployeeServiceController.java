package com.spring.login.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.login.models.EmployeeService;
import com.spring.login.services.EmployeeServiceService;

@RestController
@RequestMapping(path = "/api/employeeService", produces = { MediaType.APPLICATION_JSON_VALUE,
		MediaType.APPLICATION_XML_VALUE })
@CrossOrigin(origins = "*")
public class EmployeeServiceController {

	@Autowired
	EmployeeServiceService employeeServiceService;

	/**
	 * Este metodo se usa para añadir un servicio a un empleado
	 * @param employeeService
	 * @return
	 */
	@PostMapping()
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	public ResponseEntity<EmployeeService> postEmployeeService(@RequestBody EmployeeService employeeService) {
		return new ResponseEntity<>(employeeServiceService.save(employeeService), HttpStatus.OK);
	}
	
	//TODO serviciosEmpleadosGet y siguientes if necessary
}
