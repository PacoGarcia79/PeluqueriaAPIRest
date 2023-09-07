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
import com.spring.login.models.Service;
import com.spring.login.models.EmployeeService;
import com.spring.login.models.User;
import com.spring.login.repository.EmployeeServiceRepository;
import com.spring.login.services.EmployeeServiceService;
import com.spring.login.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeServiceControllerTest {

	@MockBean
	private EmployeeServiceRepository servicioEmpleadoRepository;
	
	@MockBean
	private EmployeeServiceService servicioEmpleadoService;

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void postServicioEmpleadoOkTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		EmployeeService servicioEmpleado1 = new EmployeeService();
		servicioEmpleado1.setIdServicioempleado(1l);
		Service servicio1 = new Service();
		servicio1.setIdServicio(1l);
		User user1 = new User();
		user1.setId(1l);
		
		servicioEmpleado1.setServicio(servicio1);
		servicioEmpleado1.setUser(user1);

		when(servicioEmpleadoService.save(Mockito.any(EmployeeService.class))).thenReturn(servicioEmpleado1);

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(servicioEmpleado1);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/servicioEmpleado/")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("@.idServicioempleado").value(1));
	}
}
