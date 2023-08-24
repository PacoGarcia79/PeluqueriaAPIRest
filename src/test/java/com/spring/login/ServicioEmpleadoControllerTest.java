package com.spring.login;

import static org.mockito.Mockito.when;

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
import com.spring.login.models.Servicio;
import com.spring.login.models.ServicioEmpleado;
import com.spring.login.models.User;
import com.spring.login.repository.ServicioEmpleadoRepository;
import com.spring.login.security.services.ServicioEmpleadoService;
import com.spring.login.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ServicioEmpleadoControllerTest {

	@MockBean
	private ServicioEmpleadoRepository servicioEmpleadoRepository;
	
	@MockBean
	private ServicioEmpleadoService servicioEmpleadoService;

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void postServicioEmpleadoOkTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		ServicioEmpleado servicioEmpleado1 = new ServicioEmpleado();
		servicioEmpleado1.setIdServicioempleado(1l);
		Servicio servicio1 = new Servicio();
		servicio1.setIdServicio(1l);
		User user1 = new User();
		user1.setId(1l);
		
		servicioEmpleado1.setServicio(servicio1);
		servicioEmpleado1.setUser(user1);

		when(servicioEmpleadoService.save(Mockito.any(ServicioEmpleado.class))).thenReturn(servicioEmpleado1);

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(servicioEmpleado1);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/servicioEmpleado/")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("@.idServicioempleado").value(1));
	}
}
