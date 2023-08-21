package com.spring.login.security.services;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.login.models.Disponibilidad;
import com.spring.login.models.Horario;
import com.spring.login.repository.HorarioRepository;

@Service
public class HorarioService {

	@Autowired
	private HorarioRepository horarioRepository;

	public List<Horario> getHorarios() {
		return horarioRepository.findAll();
	}

	public List<Disponibilidad> findNoAvailabilityDates() {
		List<List<Object>> noAvailabilityDatesObj = horarioRepository.findNoAvailabilityDates();
		List<Disponibilidad> noAvailabilityDates = new ArrayList<>();

		for (List<Object> rawData : noAvailabilityDatesObj) {
			Disponibilidad disp = new Disponibilidad();
			disp.setIdDisponibilidad(((BigInteger) rawData.get(0)).longValue());
			disp.setIdUsuario(((BigInteger) rawData.get(1)).longValue());
			disp.setNombre((String) rawData.get(2));
			disp.setIdHorario(((BigInteger) rawData.get(3)).longValue());
			disp.setHora((Time) rawData.get(4));
			disp.setFechaComienzoNoDisponible((Date) rawData.get(5));
			disp.setFechaFinNoDisponible((Date) rawData.get(6));
			noAvailabilityDates.add(disp);
		}

		return noAvailabilityDates;
	}

	public int addNoAvailabilityDates(Date fechaInicio, Date fechaFin, String empleados, String horarios) {

		return horarioRepository.addNoAvailability(fechaInicio, fechaFin, empleados, horarios);
	}

	public int delNoAvailabilityDates(Date fechaInicio, Date fechaFin, String empleados, String horarios) {

		return horarioRepository.delNoAvailability(fechaInicio, fechaFin, empleados, horarios);
	}
	
	public int delNoAvailabilityDatesById(String ids) {

		return horarioRepository.delNoAvailabilityById(ids);
	}
}
