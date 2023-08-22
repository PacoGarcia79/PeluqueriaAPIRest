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

import com.spring.login.models.ProductosGrupo;
import com.spring.login.security.services.ProductosGrupoService;

@RestController
@RequestMapping(path = "/api/productosGrupo", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@CrossOrigin(origins = "*")
public class ProductosGrupoController {
	
	@Autowired
	private ProductosGrupoService productosGrupoService;

	@GetMapping("/getProductosGrupo")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN') or hasRole('CLIENTE')")
	@ResponseBody
	public ResponseEntity<List<ProductosGrupo>> getProductosGrupo() {
		List<ProductosGrupo> productosGrupo = productosGrupoService.findAll();

		return productosGrupo.isEmpty() ? ResponseEntity.noContent().build() : new ResponseEntity<>(productosGrupo, HttpStatus.OK);
	}
	
	@PostMapping()
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	public ResponseEntity<ProductosGrupo> postProductosGrupo(@RequestBody ProductosGrupo productosGrupo) {
		return new ResponseEntity<>(productosGrupoService.save(productosGrupo), HttpStatus.OK);
	}
	
	@PutMapping()
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	public ResponseEntity<ProductosGrupo> putProductosGrupo(@RequestBody ProductosGrupo productosGrupo) {
		return new ResponseEntity<>(productosGrupoService.save(productosGrupo), HttpStatus.OK);
	}
	
	@GetMapping("/{nombreGrupo}")
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN') or hasRole('CLIENTE')")
	public ResponseEntity<Long> getProductosGrupoIdByNombreGrupo(@PathVariable(name= "nombreGrupo") String nombreGrupo) {
		Long productosGrupoId = productosGrupoService.findIdByNombreGrupo(nombreGrupo);
		
		return productosGrupoId == null ? ResponseEntity.noContent().build() : new ResponseEntity<>(productosGrupoId, HttpStatus.OK);
	}
	
	
}
