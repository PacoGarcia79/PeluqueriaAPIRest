package com.spring.login;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.spring.login.repository.ServiceRepository;
import com.spring.login.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ServiceControllerTest {

	@MockBean
	private ServiceRepository servicioRepository;

	@Autowired
	private MockMvc mockMvc;

	@Test
	// @WithMockUser(roles={"EMPLEADO"})
	// @WithUserDetails("toni")
	public void getAllServicesOkTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		Service servicio1 = new Service();
		Service servicio2 = new Service();
		servicio1.setNombre("Corte Pelo");
		servicio2.setNombre("Corte Barba");

		List<Service> servicios = new ArrayList<>();

		servicios.add(servicio1);
		servicios.add(servicio2);

		when(servicioRepository.findAll()).thenReturn(servicios);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/servicio/getServicios").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("@.[0].nombre").value("Corte Pelo"));

	}
	
	@Test
	public void getServiceByIdOkTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		Service servicio1 = new Service();
		servicio1.setNombre("Corte Pelo");
		
		Optional<Service> optServ = Optional.of(servicio1);

		when(servicioRepository.findById(Mockito.anyLong())).thenReturn(optServ);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/servicio/{id}", 1).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("@.nombre").value("Corte Pelo"));

	}

	@Test
	// @WithMockUser(roles = "USER")
	//@WithUserDetails("chisco")
	public void testUnauthorizedUser() throws Exception {
		
		//mockAuthUser();
		TestUtils.mockAuthUser("CLIENTE");
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/servicio/getServicios").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden()).andExpect(jsonPath("$").doesNotExist());

		verify(servicioRepository, never()).findAll();
	}
	
	@Test
	public void postServiceOkTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		Service servicio1 = new Service();
		servicio1.setNombre("Corte Pelo");

		when(servicioRepository.save(Mockito.any(Service.class))).thenReturn(servicio1);

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(servicio1);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/servicio/")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("@.nombre").value("Corte Pelo"));
	}
	
	@Test
	public void putServiceOkTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		Service servicio1 = new Service();
		servicio1.setNombre("Corte Pelo");

		when(servicioRepository.save(Mockito.any(Service.class))).thenReturn(servicio1);

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(servicio1);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/api/servicio/")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("@.nombre").value("Corte Pelo"));
	}
	
	@Test
	public void deleteServiceOkTest() throws Exception {
		
		TestUtils.mockAuthUser("EMPLEADO");
		
		Service servicio1 = new Service();
		servicio1.setNombre("Corte Pelo");
		servicio1.setIdServicio(1l);
		
		Optional<Service> optionalServicio = Optional.of(servicio1);
		
		when(servicioRepository.findById(Mockito.anyLong())).thenReturn(optionalServicio);
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/servicio/1"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void deleteServiceKoTest() throws Exception {
		
		TestUtils.mockAuthUser("EMPLEADO");
		
		Service servicio1 = new Service();
		servicio1.setNombre("Corte Pelo");
		servicio1.setIdServicio(1l);
				
		when(servicioRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/servicio/1"))
		.andExpect(MockMvcResultMatchers.status().is4xxClientError());		
	}

}

