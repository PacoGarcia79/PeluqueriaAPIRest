package com.spring.peluqueria;

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
import com.spring.peluqueria.models.Service;
import com.spring.peluqueria.repository.ServiceRepository;
import com.spring.peluqueria.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ServiceControllerTest {

	@MockBean
	private ServiceRepository serviceRepository;

	@Autowired
	private MockMvc mockMvc;

	@Test
	// @WithMockUser(roles={"EMPLEADO"})
	// @WithUserDetails("toni")
	public void getAllServicesOkTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		Service service1 = new Service();
		Service service2 = new Service();
		service1.setNombre("Corte Pelo");
		service2.setNombre("Corte Barba");

		List<Service> services = new ArrayList<>();

		services.add(service1);
		services.add(service2);

		when(serviceRepository.findAll()).thenReturn(services);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/service/getServices").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("@.[0].nombre").value("Corte Pelo"));

	}
	
	@Test
	public void getServiceByIdOkTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		Service service1 = new Service();
		service1.setNombre("Corte Pelo");
		
		Optional<Service> optServ = Optional.of(service1);

		when(serviceRepository.findById(Mockito.anyLong())).thenReturn(optServ);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/service/{id}", 1).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("@.nombre").value("Corte Pelo"));

	}

	@Test
	// @WithMockUser(roles = "USER")
	//@WithUserDetails("chisco")
	public void testUnauthorizedUser() throws Exception {
		
		//mockAuthUser();
		TestUtils.mockAuthUser("CLIENTE");
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/service/getServices").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden()).andExpect(jsonPath("$").doesNotExist());

		verify(serviceRepository, never()).findAll();
	}
	
	@Test
	public void postServiceOkTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		Service service1 = new Service();
		service1.setNombre("Corte Pelo");

		when(serviceRepository.save(Mockito.any(Service.class))).thenReturn(service1);

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(service1);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/service/")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("@.nombre").value("Corte Pelo"));
	}
	
	@Test
	public void putServiceOkTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		Service service1 = new Service();
		service1.setNombre("Corte Pelo");

		when(serviceRepository.save(Mockito.any(Service.class))).thenReturn(service1);

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(service1);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/api/service/")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("@.nombre").value("Corte Pelo"));
	}
	
	@Test
	public void deleteServiceOkTest() throws Exception {
		
		TestUtils.mockAuthUser("EMPLEADO");
		
		Service service1 = new Service();
		service1.setNombre("Corte Pelo");
		service1.setIdServicio(1l);
		
		Optional<Service> optionalservice = Optional.of(service1);
		
		when(serviceRepository.findById(Mockito.anyLong())).thenReturn(optionalservice);
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/service/1"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void deleteServiceKoTest() throws Exception {
		
		TestUtils.mockAuthUser("EMPLEADO");
		
		Service service1 = new Service();
		service1.setNombre("Corte Pelo");
		service1.setIdServicio(1l);
				
		when(serviceRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/service/1"))
		.andExpect(MockMvcResultMatchers.status().is4xxClientError());		
	}

}

