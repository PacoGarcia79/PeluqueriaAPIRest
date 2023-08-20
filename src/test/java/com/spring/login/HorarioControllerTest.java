package com.spring.login;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.spring.login.models.Horario;
import com.spring.login.repository.HorarioRepository;
import com.spring.login.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HorarioControllerTest {
	
	@MockBean
	private HorarioRepository horarioRepository;

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	// @WithMockUser(roles={"EMPLEADO"})
	// @WithUserDetails("toni")
	public void getAllHorariosOkTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		Horario horario1 = new Horario();
		Horario horario2 = new Horario();
		LocalTime localTime1 = LocalTime.of(10, 30, 0);
		LocalTime localTime2 = LocalTime.of(11, 30, 0);
		horario1.setHora(Time.valueOf(localTime1));
		horario2.setHora(Time.valueOf(localTime2));

		List<Horario> horarios = new ArrayList<>();

		horarios.add(horario1);
		horarios.add(horario2);

		when(horarioRepository.findAll()).thenReturn(horarios);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/horario/getHorarios").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("@.[0].hora").value("10:30:00"));

	}

}
