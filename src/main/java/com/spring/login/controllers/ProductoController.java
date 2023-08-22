package com.spring.login.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.login.models.Producto;
import com.spring.login.payload.response.MessageResponse;
import com.spring.login.security.services.ProductoService;

@RestController
@RequestMapping(path = "/api/producto", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@CrossOrigin(origins = "*")
public class ProductoController {
	
	@Autowired
	private ProductoService productoService;

	@GetMapping("/getProductos")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN') or hasRole('CLIENTE')")
	@ResponseBody
	public ResponseEntity<List<Producto>> getProductos() {
		List<Producto> productos = productoService.findAll();

		return productos.isEmpty() ? ResponseEntity.noContent().build() : new ResponseEntity<>(productos, HttpStatus.OK);
	}
	
	@GetMapping("/{grupo}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN') or hasRole('CLIENTE')")
	@ResponseBody
	public ResponseEntity<List<Producto>> getProductosByGrupo(@PathVariable("grupo") String grupo) {
		List<Producto> productosByGrupo = productoService.findProductsByGroup(grupo);

		return productosByGrupo.isEmpty() ? ResponseEntity.noContent().build() : new ResponseEntity<>(productosByGrupo, HttpStatus.OK);
	}
	
	@PostMapping()
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	public ResponseEntity<Producto> postProducto(@RequestBody Producto producto) {
		return new ResponseEntity<>(productoService.save(producto), HttpStatus.OK);
	}
	
	@PutMapping()
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	public ResponseEntity<Producto> putProducto(@RequestBody Producto producto) {
		return new ResponseEntity<>(productoService.save(producto), HttpStatus.OK);
	}
	
	@DeleteMapping("/{idProducto}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deleteServicio(@PathVariable(name = "idProducto") Long id) {

		Optional<Producto> optionalValue = productoService.findById(id);

		if (optionalValue.isPresent()) {
			productoService.deleteById(id);
			return new ResponseEntity<>(new MessageResponse("Borrado correctamente"), 
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new MessageResponse("Error al borrar - No se encuentra el registro"), 
					HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/search/{query}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN') or hasRole('CLIENTE')")
	@ResponseBody
	public ResponseEntity<List<Producto>> getProductosByQuerySearch(@PathVariable("query") String query) {
		List<Producto> productosByQuerySearch = productoService.findProductsByQuerySearch(query);

		return productosByQuerySearch.isEmpty() ? ResponseEntity.noContent().build() : new ResponseEntity<>(productosByQuerySearch, HttpStatus.OK);
	}

}
