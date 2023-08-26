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

import com.spring.login.models.Product;
import com.spring.login.payload.response.MessageResponse;
import com.spring.login.security.services.ProductService;

@RestController
@RequestMapping(path = "/api/producto", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@CrossOrigin(origins = "*")
public class ProductController {
	
	@Autowired
	private ProductService productoService;

	/**
	 * Este metodo se usa para obtener el listado de todos los productos
	 * @return
	 */
	@GetMapping("/getProductos")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN') or hasRole('CLIENTE')")
	@ResponseBody
	public ResponseEntity<List<Product>> getProductos() {
		List<Product> productos = productoService.findAll();

		return productos.isEmpty() ? ResponseEntity.noContent().build() : new ResponseEntity<>(productos, HttpStatus.OK);
	}
	
	/**
	 * Este metodo se usa para obtener el listado de todos los productos en un grupo determinado
	 * @param grupo
	 * @return
	 */
	@GetMapping("/{grupo}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN') or hasRole('CLIENTE')")
	@ResponseBody
	public ResponseEntity<List<Product>> getProductosByGrupo(@PathVariable("grupo") String grupo) {
		List<Product> productosByGrupo = productoService.findProductsByGroup(grupo);

		return productosByGrupo.isEmpty() ? ResponseEntity.noContent().build() : new ResponseEntity<>(productosByGrupo, HttpStatus.OK);
	}
	
	/**
	 * Este metodo se usa para añadir un producto
	 * @param producto
	 * @return
	 */
	@PostMapping()
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	public ResponseEntity<Product> postProducto(@RequestBody Product producto) {
		return new ResponseEntity<>(productoService.save(producto), HttpStatus.OK);
	}
	
	/**
	 * Este metodo se usa para modificar un producto existente
	 * @param producto
	 * @return
	 */
	@PutMapping()
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	public ResponseEntity<Product> putProducto(@RequestBody Product producto) {
		return new ResponseEntity<>(productoService.save(producto), HttpStatus.OK);
	}
	
	/**
	 * Este metodo se usa para eliminar un producto existente
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{idProducto}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deleteServicio(@PathVariable(name = "idProducto") Long id) {

		Optional<Product> optionalValue = productoService.findById(id);

		if (optionalValue.isPresent()) {
			productoService.deleteById(id);
			return new ResponseEntity<>(new MessageResponse("Borrado correctamente"), 
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new MessageResponse("Error al borrar - No se encuentra el registro"), 
					HttpStatus.CONFLICT);
		}
	}
	
	/**
	 * Este metodo se usa para obtener el listado de productos obtenidos en una búsqueda
	 * @param query
	 * @return
	 */
	@GetMapping("/search/{query}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN') or hasRole('CLIENTE')")
	@ResponseBody
	public ResponseEntity<List<Product>> getProductosByQuerySearch(@PathVariable("query") String query) {
		List<Product> productosByQuerySearch = productoService.findProductsByQuerySearch(query);

		return productosByQuerySearch.isEmpty() ? ResponseEntity.noContent().build() : new ResponseEntity<>(productosByQuerySearch, HttpStatus.OK);
	}

}
