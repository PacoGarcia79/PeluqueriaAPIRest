package com.spring.login.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.login.models.EmployeeService;
import com.spring.login.repository.EmployeeServiceRepository;

@Service
public class EmployeeServiceService {

	@Autowired
	EmployeeServiceRepository employeeServiceRepository;
	
	public EmployeeService save(EmployeeService employeeService) {
		return employeeServiceRepository.save(employeeService);
	}
}
