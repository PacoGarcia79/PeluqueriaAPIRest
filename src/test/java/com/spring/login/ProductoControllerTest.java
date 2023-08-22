package com.spring.login;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
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
import com.spring.login.models.Servicio;
import com.spring.login.repository.ProductoRepository;
import com.spring.login.security.services.ProductoService;
import com.spring.login.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductoControllerTest {

	@MockBean
	private ProductoRepository productoRepository;

	@MockBean
	private ProductoService productoService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getAllProductosOkTest() throws Exception {

		TestUtils.mockAuthUser("CLIENTE");

		Producto producto1 = new Producto();
		Producto producto2 = new Producto();
		producto1.setNombre("Cera Pelo");
		producto2.setNombre("Aceite Barba");

		List<Producto> productos = new ArrayList<>();

		productos.add(producto1);
		productos.add(producto2);

		when(productoService.findAll()).thenReturn(productos);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/producto/getProductos").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("@.[0].nombre").value("Cera Pelo"));

	}

	@Test
	public void getAllProductosByGroupOkTest() throws Exception {

		TestUtils.mockAuthUser("CLIENTE");

		Producto producto1 = new Producto();
		Producto producto2 = new Producto();

		producto1.setNombre("Cera Pelo");
		producto2.setNombre("Hair Styler");
		ProductosGrupo grupo1 = new ProductosGrupo();
		grupo1.setNombreGrupo("Pelo");
		producto1.setProductosgrupo(grupo1);
		producto2.setProductosgrupo(grupo1);

		List<Producto> productos = new ArrayList<>();

		productos.add(producto1);
		productos.add(producto2);

		when(productoService.findProductsByGroup(Mockito.anyString())).thenReturn(productos);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/producto/{grupo}", "Pelo").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

	}

	@Test
	public void postProductoOkTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		Producto producto1 = new Producto();

		producto1.setNombre("Cera Pelo");
		ProductosGrupo grupo1 = new ProductosGrupo();
		grupo1.setNombreGrupo("Pelo");
		producto1.setProductosgrupo(grupo1);

		when(productoService.save(Mockito.any(Producto.class))).thenReturn(producto1);

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(producto1);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/producto/").content(json)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("@.nombre").value("Cera Pelo"));
	}

	@Test
	public void putProductoOkTest() throws Exception {

		TestUtils.mockAuthUser("EMPLEADO");

		Producto producto1 = new Producto();

		producto1.setNombre("Cera Pelo");
		ProductosGrupo grupo1 = new ProductosGrupo();
		grupo1.setNombreGrupo("Pelo");
		producto1.setProductosgrupo(grupo1);

		when(productoService.save(Mockito.any(Producto.class))).thenReturn(producto1);

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(producto1);

		mockMvc.perform(MockMvcRequestBuilders.put("/api/producto/").content(json)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("@.nombre").value("Cera Pelo"));
	}
	
	@Test
	public void deleteProductoOkTest() throws Exception {
		
		TestUtils.mockAuthUser("EMPLEADO");
		
		Producto producto1 = new Producto();

		producto1.setNombre("Cera Pelo");
		ProductosGrupo grupo1 = new ProductosGrupo();
		grupo1.setNombreGrupo("Pelo");
		producto1.setProductosgrupo(grupo1);
		producto1.setIdProducto(12l);
		
		Optional<Producto> optionalProducto = Optional.of(producto1);
		
		when(productoService.findById(Mockito.anyLong())).thenReturn(optionalProducto);
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/producto/12"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void deleteProductoKoTest() throws Exception {
		
		TestUtils.mockAuthUser("EMPLEADO");
		
		Producto producto1 = new Producto();

		producto1.setNombre("Cera Pelo");
		ProductosGrupo grupo1 = new ProductosGrupo();
		grupo1.setNombreGrupo("Pelo");
		producto1.setProductosgrupo(grupo1);
		producto1.setIdProducto(12l);
				
		when(productoService.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/producto/12"))
		.andExpect(MockMvcResultMatchers.status().is4xxClientError());		
	}
	
	@Test
	public void getProductosBySearchOkTest() throws Exception {

		TestUtils.mockAuthUser("CLIENTE");

		Producto producto1 = new Producto();

		producto1.setNombre("Cera Pelo");
		ProductosGrupo grupo1 = new ProductosGrupo();
		grupo1.setNombreGrupo("Pelo");
		producto1.setProductosgrupo(grupo1);

		List<Producto> productos = new ArrayList<>();

		productos.add(producto1);

		when(productoService.findProductsByQuerySearch(Mockito.anyString())).thenReturn(productos);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/producto/search/{query}", "Pelo").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));

	}
}
