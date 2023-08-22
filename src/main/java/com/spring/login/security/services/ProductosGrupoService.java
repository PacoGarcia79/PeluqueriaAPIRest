package com.spring.login.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.login.models.ProductosGrupo;
import com.spring.login.repository.ProductosGrupoRepository;

@Service
public class ProductosGrupoService {

	@Autowired
	ProductosGrupoRepository productosGrupoRepository;
	
	public List<ProductosGrupo> findAll(){
		return productosGrupoRepository.findAll();
	}
	
	public ProductosGrupo save(ProductosGrupo productosGrupo) {
		return productosGrupoRepository.save(productosGrupo);
	}
	
	public Long findIdByNombreGrupo(String nombreGrupo) {
		return productosGrupoRepository.findIdByNombreGrupo(nombreGrupo);
	}
}
