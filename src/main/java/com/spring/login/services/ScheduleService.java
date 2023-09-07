package com.spring.login.services;

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
	private ScheduleRepository scheduleRepository;

	public List<Schedule> getSchedule() {
		return scheduleRepository.findAll();
	}

	public List<Availability> findNoAvailabilityDates() {
		List<List<Object>> noAvailabilityDatesObj = scheduleRepository.findNoAvailabilityDates();
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

	public int addNoAvailabilityDates(Date fechaInicio, Date fechaFin, String empleados, String schedule) {

		return scheduleRepository.addNoAvailability(fechaInicio, fechaFin, empleados, schedule);
	}

	public int delNoAvailabilityDates(Date fechaInicio, Date fechaFin, String empleados, String schedule) {

		return scheduleRepository.delNoAvailability(fechaInicio, fechaFin, empleados, schedule);
	}
	
	public int delNoAvailabilityDatesById(String ids) {

		return scheduleRepository.delNoAvailabilityById(ids);
	}
	
	public Schedule save(Schedule schedule) {
		return scheduleRepository.save(schedule);
	}
	
	public List<Schedule> findEmployeeFreeScheduleByDate(Long idUser, Date date) {
		List<List<Object>> freeScheduleByDateObj = scheduleRepository.findEmployeeFreeScheduleByDate(idUser, date);
		List<Schedule> freeScheduleByDate = new ArrayList<>();

		for (List<Object> rawData : freeScheduleByDateObj) {
			Schedule schedule = new Schedule();
			schedule.setIdHorario(((BigInteger) rawData.get(0)).longValue());
			schedule.setHora((Time) rawData.get(1));
			freeScheduleByDate.add(schedule);
		}

		return freeScheduleByDate;
	}
	
	public List<Schedule> findFreeScheduleByDate(Date date) {
		List<List<Object>> freeScheduleByDateObj = scheduleRepository.findFreeScheduleByDate(date);
		List<Schedule> freeScheduleByDate = new ArrayList<>();

		for (List<Object> rawData : freeScheduleByDateObj) {
			Schedule schedule = new Schedule();
			schedule.setIdHorario(((BigInteger) rawData.get(0)).longValue());
			schedule.setHora((Time) rawData.get(1));
			freeScheduleByDate.add(schedule);
		}

		return freeScheduleByDate;
	}
}
