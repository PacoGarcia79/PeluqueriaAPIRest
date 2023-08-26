package com.spring.login.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.login.models.EmployeeService;
import com.spring.login.repository.EmployeeServiceRepository;

@Service
public class EmployeeServiceService {

	@Autowired
	EmployeeServiceRepository servicioEmpleadoRepository;
	
	public EmployeeService save(EmployeeService servicioEmpleado) {
		return servicioEmpleadoRepository.save(servicioEmpleado);
	}
}
