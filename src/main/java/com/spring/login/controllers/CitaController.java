package com.spring.login.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.login.models.Cita;
import com.spring.login.repository.CitaRepository;

@RestController
@RequestMapping(path = "/api/cita",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
@CrossOrigin(origins="*")
public class CitaController {
	
	@Autowired
	CitaRepository citaRepository;
	
	@GetMapping("/getCitas")
	@PreAuthorize("hasRole('CLIENTE') or hasRole('EMPLEADO') or hasRole('ADMIN')")
    @ResponseBody
    public List<Cita> getAllCitas(){
        return citaRepository.findAll();
    }

}
