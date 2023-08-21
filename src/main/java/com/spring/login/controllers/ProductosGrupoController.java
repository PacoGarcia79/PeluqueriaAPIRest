package com.spring.login.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
	@ResponseBody
	public ResponseEntity<List<ProductosGrupo>> getProductosGrupo() {
		List<ProductosGrupo> productosGrupo = productosGrupoService.findAll();

		return productosGrupo.isEmpty() ? ResponseEntity.noContent().build() : new ResponseEntity<>(productosGrupo, HttpStatus.OK);
	}
}
