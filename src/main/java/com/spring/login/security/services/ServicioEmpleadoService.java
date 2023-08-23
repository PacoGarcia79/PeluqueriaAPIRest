package com.spring.login.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.login.models.ServicioEmpleado;
import com.spring.login.repository.ServicioEmpleadoRepository;

@Service
public class ServicioEmpleadoService {

	@Autowired
	ServicioEmpleadoRepository servicioEmpleadoRepository;
	
	public ServicioEmpleado save(ServicioEmpleado servicioEmpleado) {
		return servicioEmpleadoRepository.save(servicioEmpleado);
	}
}
