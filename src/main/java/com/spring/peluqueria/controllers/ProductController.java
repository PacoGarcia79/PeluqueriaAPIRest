package com.spring.peluqueria.controllers;

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

import com.spring.peluqueria.constants.ApiConstants;
import com.spring.peluqueria.models.Product;
import com.spring.peluqueria.payload.response.MessageResponse;
import com.spring.peluqueria.services.ProductService;

@RestController
@RequestMapping(path = "/api/product", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@CrossOrigin(origins = "*")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	/**
	 * Este metodo se usa para obtener el listado de todos los productos
	 * @return
	 */
	@GetMapping("/getProducts")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN') or hasRole('CLIENTE')")
	@ResponseBody
	public ResponseEntity<List<Product>> getProducts() {
		List<Product> products = productService.findAll();

		return products.isEmpty() ? ResponseEntity.noContent().build() : new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	/**
	 * Este metodo se usa para obtener el listado de todos los productos en un grupo determinado
	 * @param grupo
	 * @return
	 */
	@GetMapping("/{group}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN') or hasRole('CLIENTE')")
	@ResponseBody
	public ResponseEntity<List<Product>> getProductsByGroup(@PathVariable("group") String grupo) {
		List<Product> productsByGrupo = productService.findProductsByGroup(grupo);

		return productsByGrupo.isEmpty() ? ResponseEntity.noContent().build() : new ResponseEntity<>(productsByGrupo, HttpStatus.OK);
	}
	
	/**
	 * Este metodo se usa para añadir un producto
	 * @param product
	 * @return
	 */
	@PostMapping()
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	public ResponseEntity<Product> postProduct(@RequestBody Product product) {
		return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
	}
	
	/**
	 * Este metodo se usa para modificar un producto existente
	 * @param product
	 * @return
	 */
	@PutMapping()
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	public ResponseEntity<Product> putProduct(@RequestBody Product product) {
		return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
	}
	
	/**
	 * Este metodo se usa para eliminar un producto existente
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{idProducto}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deleteProduct(@PathVariable(name = "idProducto") Long id) {

		Optional<Product> optionalValue = productService.findById(id);

		if (optionalValue.isPresent()) {
			productService.deleteById(id);
			return new ResponseEntity<>(new MessageResponse(ApiConstants.DELETE_OK), 
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new MessageResponse(ApiConstants.DELETE_OK), 
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
	public ResponseEntity<List<Product>> getProductsByQuerySearch(@PathVariable("query") String query) {
		List<Product> productsByQuerySearch = productService.findProductsByQuerySearch(query);

		return productsByQuerySearch.isEmpty() ? ResponseEntity.noContent().build() : new ResponseEntity<>(productsByQuerySearch, HttpStatus.OK);
	}

}
