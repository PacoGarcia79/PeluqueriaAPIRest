package com.spring.login.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.login.models.ProductGroup;
import com.spring.login.services.ProductGroupService;

@RestController
@RequestMapping(path = "/api/productsGroup", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@CrossOrigin(origins = "*")
public class ProductGroupController {
	
	@Autowired
	private ProductGroupService productsGrupoService;

	/**
	 * Este metodo se usa para obtener el listado de todos los grupos de productos.
	 * @return
	 */
	@GetMapping("/getProductsGroup")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN') or hasRole('CLIENTE')")
	@ResponseBody
	public ResponseEntity<List<ProductGroup>> getProductsGroup() {
		List<ProductGroup> productsGrupo = productsGrupoService.findAll();

		return productsGrupo.isEmpty() ? ResponseEntity.noContent().build() : new ResponseEntity<>(productsGrupo, HttpStatus.OK);
	}
	
	/**
	 * Este metodo se usa para añadir un grupo de productos
	 * @param productsGroup
	 * @return
	 */
	@PostMapping()
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	public ResponseEntity<ProductGroup> postProductsGroup(@RequestBody ProductGroup productsGroup) {
		return new ResponseEntity<>(productsGrupoService.save(productsGroup), HttpStatus.OK);
	}
	
	/**
	 * Este metodo se usa para modificar un grupo de productos existente
	 * @param productsGroup
	 * @return
	 */
	@PutMapping()
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	public ResponseEntity<ProductGroup> putProductsGroup(@RequestBody ProductGroup productsGroup) {
		return new ResponseEntity<>(productsGrupoService.save(productsGroup), HttpStatus.OK);
	}
	
	/**
	 * Este metodo se usa para obtener el id de un grupo de productos, mediante el nombre
	 * @param nombreGrupo
	 * @return
	 */
	@GetMapping("/{nombreGrupo}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN') or hasRole('CLIENTE')")
	public ResponseEntity<Long> getProductsGrupoIdByGroupName(@PathVariable(name= "nombreGrupo") String nombreGrupo) {
		Long productsGrupoId = productsGrupoService.findIdByNombreGrupo(nombreGrupo);
		
		return productsGrupoId == null ? ResponseEntity.noContent().build() : new ResponseEntity<>(productsGrupoId, HttpStatus.OK);
	}
	
	
}
