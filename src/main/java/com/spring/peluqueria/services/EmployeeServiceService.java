package com.spring.peluqueria.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.peluqueria.models.EmployeeService;
import com.spring.peluqueria.repository.EmployeeServiceRepository;

@Service
public class EmployeeServiceService {

	@Autowired
	EmployeeServiceRepository employeeServiceRepository;
	
	public EmployeeService save(EmployeeService employeeService) {
		return employeeServiceRepository.save(employeeService);
	}
}
