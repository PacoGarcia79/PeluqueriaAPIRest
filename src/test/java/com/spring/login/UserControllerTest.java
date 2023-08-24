package com.spring.login;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
import com.spring.login.models.Producto;
import com.spring.login.models.ProductosGrupo;
import com.spring.login.models.User;
import com.spring.login.repository.UserRepository;
import com.spring.login.security.services.UserService;
import com.spring.login.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private UserService userService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getAllUsersOkTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		User user1 = new User();
		User user2 = new User();

		user1.setUsername("pepegar");
		user2.setUsername("anagonz");

		List<User> users = new ArrayList<>();

		users.add(user1);
		users.add(user2);

		when(userService.findAllUsers()).thenReturn(users);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/user/getUsers").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("@.[0].username").value("pepegar"));

	}

	@Test
	public void getAllUsersByRoleOkTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		User user1 = new User();
		User user2 = new User();

		user1.setUsername("pepegar");
		user2.setUsername("anagonz");

		List<User> users = new ArrayList<>();

		users.add(user1);
		users.add(user2);

		when(userService.findAllUsersByRole(Mockito.anyString())).thenReturn(users);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/user/getUsers/{role}", "ROLE_EMPLEADO")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("@.[0].username").value("pepegar"));

	}

	@Test
	public void putUserOkTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		User user1 = new User();
		user1.setUsername("pepegar");

		when(userService.save(Mockito.any(User.class))).thenReturn(user1);

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(user1);

		mockMvc.perform(MockMvcRequestBuilders.put("/api/user/").content(json).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("@.username").value("pepegar"));
	}

	@Test
	public void postUserOkTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		User user1 = new User();
		user1.setUsername("pepegar");

		when(userService.save(Mockito.any(User.class))).thenReturn(user1);

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(user1);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/user/").content(json).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("@.username").value("pepegar"));
	}

	@Test
	public void deleteUserOkTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		User user1 = new User();
		user1.setId(12l);
		user1.setUsername("pepegar");

		when(userService.findUserById(Mockito.anyLong())).thenReturn(user1);

		mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/12")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void deleteProductoKoTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		User user1 = new User();
		user1.setId(12l);
		user1.setUsername("pepegar");

		when(userService.findUserById(Mockito.anyLong())).thenReturn(null);

		mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/12"))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	
	@Test
	public void getUserByEmailOkTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		User user1 = new User();

		user1.setUsername("pepegar");
		user1.setEmail("pepe@test.com");

		when(userService.findByEmail(Mockito.anyString())).thenReturn(user1);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/user/{email}", "pepe@test.com")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("@.username").value("pepegar"));

	}
	
	@Test
	public void getUserByIdOkTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		User user1 = new User();

		user1.setId(1l);
		user1.setUsername("pepegar");
		user1.setEmail("pepe@test.com");

		when(userService.findUserById(Mockito.anyLong())).thenReturn(user1);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/user/id/{id}", 1)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("@.username").value("pepegar"));

	}
	
	@Test
	public void getUsersWithoutServiceOkTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		User user1 = new User();
		User user2 = new User();

		user1.setUsername("pepegar");
		user2.setUsername("anagonz");

		List<User> users = new ArrayList<>();

		users.add(user1);
		users.add(user2);

		when(userService.findUsersWithoutService(1l)).thenReturn(users);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/user/noService/{idServicio}", 1)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("@.[0].username").value("pepegar"));

	}
	
	@Test
	public void addHorariosToUserOkTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		User user1 = new User();
		user1.setId(12l);
		user1.setUsername("pepegar");

		when(userRepository.addHorariosToUser()).thenReturn(user1.getId());
		when(userService.addHorariosToUser()).thenReturn(true);

		mockMvc.perform(MockMvcRequestBuilders.put("/api/user/horarios/")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}
	
	

}
