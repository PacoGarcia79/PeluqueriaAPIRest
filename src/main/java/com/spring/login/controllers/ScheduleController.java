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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.login.constants.ApiConstants;
import com.spring.login.models.Availability;
import com.spring.login.models.Schedule;
import com.spring.login.models.Service;
import com.spring.login.payload.response.MessageResponse;
import com.spring.login.services.ScheduleService;

@RestController
@RequestMapping(path = "/api/schedule", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@CrossOrigin(origins = "*")
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;

	/**
	 * Este metodo se usa para obtener el listado de schedule
	 * @return
	 */
	@GetMapping("/getSchedule")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	@ResponseBody
	public ResponseEntity<List<Schedule>> getSchedule() {
		List<Schedule> schedule = scheduleService.getSchedule();

		return schedule.isEmpty() ? ResponseEntity.noContent().build() : new ResponseEntity<>(schedule, HttpStatus.OK);
	}

	/**
	 * Este metodo se usa para obtener el listado de no disponibilidad
	 * @return
	 * @throws SQLException
	 */
	@GetMapping("/noAvailabilityDates")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	@ResponseBody
	public ResponseEntity<List<Availability>> findNoAvailabilityDates() throws SQLException {

		List<Availability> noAvailabilityDates = scheduleService.findNoAvailabilityDates();

		return noAvailabilityDates.isEmpty() ? ResponseEntity.noContent().build()
				: new ResponseEntity<>(noAvailabilityDates, HttpStatus.OK);
	}

	/**
	 * Este metodo se usa para añadir el/los horario/s del/los empleado/s al listado de no disponibilidad,
     * para una fecha o un periodo de fechas
	 * @param fechaComienzo
	 * @param fechaFinal
	 * @param empleados
	 * @param schedule
	 * @return
	 */
	@PutMapping("/addNoAvailabilityDates/{fechaComienzo}/{fechaFin}/{empleados}/{horas}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> addNoAvailabilityDates(@PathVariable("fechaComienzo") Date fechaComienzo,
			@PathVariable("fechaFin") Date fechaFinal, @PathVariable("empleados") String empleados,
			@PathVariable("horas") String schedule) {

		int result = scheduleService.addNoAvailabilityDates(fechaComienzo, fechaFinal, empleados, schedule);

		return getResponseMessage(result);
	}
	
	/**
	 * Este metodo se usa para eliminar el/los horario/s del/los empleado/s del listado de no disponibilidad,
     * para una fecha o un periodo de fechas
	 * @param fechaComienzo
	 * @param fechaFinal
	 * @param empleados
	 * @param schedule
	 * @return
	 */
	@PutMapping("/delNoAvailabilityDates/{fechaComienzo}/{fechaFin}/{empleados}/{horas}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> delNoAvailabilityDates(@PathVariable("fechaComienzo") Date fechaComienzo,
			@PathVariable("fechaFin") Date fechaFinal, @PathVariable("empleados") String empleados,
			@PathVariable("horas") String schedule) {

		int result = scheduleService.delNoAvailabilityDates(fechaComienzo, fechaFinal, empleados, schedule);

		return getResponseMessage(result);
	}
	
	/**
	 * Este metodo se usa para eliminar el/los horario/s del/los empleado/s del
     * listado de no disponibilidad, usando los ids de los registros.
	 * @param ids
	 * @return
	 */
	@PutMapping("/delNoAvailabilityDatesById/{ids}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> delNoAvailabilityDatesById(@PathVariable("ids") String ids) {

		int result = scheduleService.delNoAvailabilityDatesById(ids);

		return getResponseMessage(result);
	}

	private ResponseEntity<MessageResponse> getResponseMessage(int result) {
		switch (result) {
		case 1:
			return new ResponseEntity<>(new MessageResponse(ApiConstants.REGISTER_OK), HttpStatus.OK);
		case 2:
			return new ResponseEntity<>(new MessageResponse(ApiConstants.REGISTER_KO), HttpStatus.CONFLICT);
		default:
			return new ResponseEntity<>(new MessageResponse(ApiConstants.REGISTER_KO2), HttpStatus.CONFLICT);
		}
	}
	
	/**
	 * Este metodo se usa para añadir un horario
	 * @param schedule
	 * @return
	 */
	@PostMapping()
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	public ResponseEntity<Schedule> postSchedule(@RequestBody Schedule schedule) {
		return new ResponseEntity<>(scheduleService.save(schedule), HttpStatus.OK);
	}
	

	/**
	 * Este metodo se usa para obtener el listado de schedule libres por
     * empleado en una fecha, para la funcionalidad de citas
	 * @param idUsuario
	 * @param date
	 * @return
	 */
	@GetMapping("/freeScheduleEmployeeDate/{id}/{date}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	@ResponseBody
	public ResponseEntity<List<Schedule>> getEmployeeFreeScheduleByDate(@PathVariable(name = "id")Long idUsuario, @PathVariable(name = "date") Date date){
		List<Schedule> scheduleFree = scheduleService.findEmployeeFreeScheduleByDate(idUsuario, date);
		
		return scheduleFree.isEmpty() ? ResponseEntity.noContent().build()
				: new ResponseEntity<>(scheduleFree, HttpStatus.OK); 
	}
	
	/**
	 * Este metodo se usa para obtener el listado de schedule libres en una
     * fecha determinada, para la funcionalidad de citas
	 * @param fecha
	 * @return
	 */
	@GetMapping("/freeScheduleDate/{date}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	@ResponseBody
	public ResponseEntity<List<Schedule>> getFreeScheduleByDate(@PathVariable(name = "date") Date date){
		List<Schedule> scheduleFree = scheduleService.findFreeScheduleByDate(date);
		
		return scheduleFree.isEmpty() ? ResponseEntity.noContent().build()
				: new ResponseEntity<>(scheduleFree, HttpStatus.OK); 
	}
	
}
