package com.spring.login.security.services;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.login.models.Availability;
import com.spring.login.models.Schedule;
import com.spring.login.repository.ScheduleRepository;

@Service
public class ScheduleService {

	@Autowired
	private ScheduleRepository horarioRepository;

	public List<Schedule> getHorarios() {
		return horarioRepository.findAll();
	}

	public List<Availability> findNoAvailabilityDates() {
		List<List<Object>> noAvailabilityDatesObj = horarioRepository.findNoAvailabilityDates();
		List<Availability> noAvailabilityDates = new ArrayList<>();

		for (List<Object> rawData : noAvailabilityDatesObj) {
			Availability disp = new Availability();
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
	
	public Schedule save(Schedule horario) {
		return horarioRepository.save(horario);
	}
	
	public List<Schedule> findEmployeeFreeScheduleByDate(Long idUsuario, Date date) {
		List<List<Object>> freeHorariosByDateObj = horarioRepository.findEmployeeFreeScheduleByDate(idUsuario, date);
		List<Schedule> freeHorariosByDate = new ArrayList<>();

		for (List<Object> rawData : freeHorariosByDateObj) {
			Schedule horario = new Schedule();
			horario.setIdHorario(((BigInteger) rawData.get(0)).longValue());
			horario.setHora((Time) rawData.get(1));
			freeHorariosByDate.add(horario);
		}

		return freeHorariosByDate;
	}
	
	public List<Schedule> findFreeScheduleByDate(Date date) {
		List<List<Object>> freeHorariosByDateObj = horarioRepository.findFreeScheduleByDate(date);
		List<Schedule> freeHorariosByDate = new ArrayList<>();

		for (List<Object> rawData : freeHorariosByDateObj) {
			Schedule horario = new Schedule();
			horario.setIdHorario(((BigInteger) rawData.get(0)).longValue());
			horario.setHora((Time) rawData.get(1));
			freeHorariosByDate.add(horario);
		}

		return freeHorariosByDate;
	}
}
