package com.spring.login;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.login.models.Availability;
import com.spring.login.models.Schedule;
import com.spring.login.models.Service;
import com.spring.login.repository.ScheduleRepository;
import com.spring.login.services.ScheduleService;
import com.spring.login.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ScheduleControllerTest {

	@MockBean
	private ScheduleRepository horarioRepository;

	@MockBean
	private ScheduleService horarioService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getAllHorariosOkTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		Schedule horario1 = new Schedule();
		Schedule horario2 = new Schedule();
		LocalTime localTime1 = LocalTime.of(10, 30, 0);
		LocalTime localTime2 = LocalTime.of(11, 30, 0);
		horario1.setHora(Time.valueOf(localTime1));
		horario2.setHora(Time.valueOf(localTime2));

		List<Schedule> horarios = new ArrayList<>();

		horarios.add(horario1);
		horarios.add(horario2);

		when(horarioService.getSchedule()).thenReturn(horarios);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/horario/getHorarios").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("@.[0].hora").value("10:30:00"));

	}

	@Test
	public void findNoAvailabilityDatesOkTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		Availability disp1 = new Availability();
		Availability disp2 = new Availability();
		LocalTime localTime1 = LocalTime.of(10, 30, 0);
		LocalTime localTime2 = LocalTime.of(11, 30, 0);
		disp1.setHora(Time.valueOf(localTime1));
		disp2.setHora(Time.valueOf(localTime2));

		List<Availability> noDispList = new ArrayList<>();

		noDispList.add(disp1);
		noDispList.add(disp2);

		when(horarioService.findNoAvailabilityDates()).thenReturn(noDispList);

		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/horario/noAvailabilityDates").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("@.[0].hora").value("10:30:00"));

	}

	@Test
	public void addNoAvailabilityDatesOkTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		String fechaInicioString = "2023-09-19";
		String fechaFinString = "2023-09-20";
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilDateInicio = formato.parse(fechaInicioString);
		java.sql.Date sqlDateInicio = new java.sql.Date(utilDateInicio.getTime());
		java.util.Date utilDateFin = formato.parse(fechaFinString);
		java.sql.Date sqlDateFin = new java.sql.Date(utilDateFin.getTime());

		when(horarioService.addNoAvailabilityDates(Mockito.any(), Mockito.any(), Mockito.anyString(),
				Mockito.anyString())).thenReturn(1);

		mockMvc.perform(MockMvcRequestBuilders.put(
				"/api/horario/addNoAvailabilityDates/{fechaComienzo}/{fechaFin}/{empleados}/{horas}", sqlDateInicio,
				sqlDateFin, "3", "1,2,3,4,5,6")).andExpect(status().isOk());
	}

	@Test
	public void addNoAvailabilityDatesKoTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		String fechaInicioString = "2023-09-19";
		String fechaFinString = "2023-09-20";
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilDateInicio = formato.parse(fechaInicioString);
		java.sql.Date sqlDateInicio = new java.sql.Date(utilDateInicio.getTime());
		java.util.Date utilDateFin = formato.parse(fechaFinString);
		java.sql.Date sqlDateFin = new java.sql.Date(utilDateFin.getTime());

		when(horarioService.addNoAvailabilityDates(Mockito.any(), Mockito.any(), Mockito.anyString(),
				Mockito.anyString())).thenReturn(0);

		mockMvc.perform(MockMvcRequestBuilders.put(
				"/api/horario/addNoAvailabilityDates/{fechaComienzo}/{fechaFin}/{empleados}/{horas}", sqlDateInicio,
				sqlDateFin, "3", "1,2,3,4,5,6")).andExpect(status().is4xxClientError());
	}

	@Test
	public void addNoAvailabilityDatesKoTest2() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		String fechaInicioString = "2023-09-19";
		String fechaFinString = "2023-09-20";
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilDateInicio = formato.parse(fechaInicioString);
		java.sql.Date sqlDateInicio = new java.sql.Date(utilDateInicio.getTime());
		java.util.Date utilDateFin = formato.parse(fechaFinString);
		java.sql.Date sqlDateFin = new java.sql.Date(utilDateFin.getTime());

		when(horarioService.addNoAvailabilityDates(Mockito.any(), Mockito.any(), Mockito.anyString(),
				Mockito.anyString())).thenReturn(2);

		mockMvc.perform(MockMvcRequestBuilders.put(
				"/api/horario/addNoAvailabilityDates/{fechaComienzo}/{fechaFin}/{empleados}/{horas}", sqlDateInicio,
				sqlDateFin, "3", "1,2,3,4,5,6")).andExpect(status().is4xxClientError());
	}

	@Test
	public void delNoAvailabilityDatesOkTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		String fechaInicioString = "2023-09-19";
		String fechaFinString = "2023-09-20";
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilDateInicio = formato.parse(fechaInicioString);
		java.sql.Date sqlDateInicio = new java.sql.Date(utilDateInicio.getTime());
		java.util.Date utilDateFin = formato.parse(fechaFinString);
		java.sql.Date sqlDateFin = new java.sql.Date(utilDateFin.getTime());

		when(horarioService.delNoAvailabilityDates(Mockito.any(), Mockito.any(), Mockito.anyString(),
				Mockito.anyString())).thenReturn(1);

		mockMvc.perform(MockMvcRequestBuilders.put(
				"/api/horario/delNoAvailabilityDates/{fechaComienzo}/{fechaFin}/{empleados}/{horas}", sqlDateInicio,
				sqlDateFin, "3", "1,2,3,4,5,6")).andExpect(status().isOk());
	}

	@Test
	public void delNoAvailabilityDatesByIdOkTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		when(horarioService.delNoAvailabilityDatesById(Mockito.anyString())).thenReturn(1);

		mockMvc.perform(MockMvcRequestBuilders.put("/api/horario/delNoAvailabilityDatesById/{ids}", "1,2,3,4,5,6"))
				.andExpect(status().isOk());
	}

	@Test
	public void postHorarioOkTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		Schedule horario1 = new Schedule();
		LocalTime localTime1 = LocalTime.of(10, 30, 0);
		horario1.setHora(Time.valueOf(localTime1));

		when(horarioService.save(Mockito.any(Schedule.class))).thenReturn(horario1);

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(horario1);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/horario/").content(json)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("@.hora").value("10:30:00"));
	}
}
