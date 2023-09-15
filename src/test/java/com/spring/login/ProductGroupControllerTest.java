package com.spring.login;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.spring.login.utils.TestUtils;
import com.spring.peluqueria.models.Product;
import com.spring.peluqueria.models.ProductGroup;
import com.spring.peluqueria.repository.ProductGroupRepository;
import com.spring.peluqueria.services.ProductGroupService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductGroupControllerTest {

	@MockBean
	private ProductGroupRepository productosGrupoRepository;

	@MockBean
	private ProductGroupService productosGrupoService;

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getAllProductosGrupoOkTest() throws Exception {

		TestUtils.mockAuthUser("CLIENTE");

		ProductGroup productosGrupo1 = new ProductGroup();
		ProductGroup productosGrupo2 = new ProductGroup();
		productosGrupo1.setNombreGrupo("Pelo");
		productosGrupo2.setNombreGrupo("Barba");

		List<ProductGroup> productosGrupos = new ArrayList<>();

		productosGrupos.add(productosGrupo1);
		productosGrupos.add(productosGrupo2);

		when(productosGrupoService.findAll()).thenReturn(productosGrupos);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/productosGrupo/getProductosGrupo").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("@.[0].nombreGrupo").value("Pelo"));

	}
	
	@Test
	public void postProductosGrupoOkTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		ProductGroup productosGrupo1 = new ProductGroup();
		productosGrupo1.setNombreGrupo("Pelo");

		when(productosGrupoService.save(Mockito.any(ProductGroup.class))).thenReturn(productosGrupo1);

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(productosGrupo1);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/productosGrupo/").content(json)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("@.nombreGrupo").value("Pelo"));
	}

	@Test
	public void putProductosGrupoOkTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		ProductGroup productosGrupo1 = new ProductGroup();
		productosGrupo1.setNombreGrupo("Pelo");

		when(productosGrupoService.save(Mockito.any(ProductGroup.class))).thenReturn(productosGrupo1);

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(productosGrupo1);

		mockMvc.perform(MockMvcRequestBuilders.put("/api/productosGrupo/").content(json)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("@.nombreGrupo").value("Pelo"));
	}
	
	@Test
	public void getAllProductosGrupoByNombreGrupoOkTest() throws Exception {

		TestUtils.mockAuthUser("CLIENTE");

		when(productosGrupoService.findIdByNombreGrupo(Mockito.anyString())).thenReturn(1L);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/productosGrupo/{nombreGrupo}", "Pelo").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}
	
	@Test
	public void getAllProductosGrupoByNombreGrupoKoTest() throws Exception {

		TestUtils.mockAuthUser("CLIENTE");

		when(productosGrupoService.findIdByNombreGrupo(Mockito.anyString())).thenReturn(null);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/productosGrupo/{nombreGrupo}", "Pelo").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful());

	}
}
