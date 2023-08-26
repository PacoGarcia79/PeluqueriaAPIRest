package com.spring.login.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.login.models.ProductGroup;
import com.spring.login.repository.ProductGroupRepository;

@Service
public class ProductGroupService {

	@Autowired
	ProductGroupRepository productosGrupoRepository;
	
	public List<ProductGroup> findAll(){
		return productosGrupoRepository.findAll();
	}
	
	public ProductGroup save(ProductGroup productosGrupo) {
		return productosGrupoRepository.save(productosGrupo);
	}
	
	public Long findIdByNombreGrupo(String nombreGrupo) {
		return productosGrupoRepository.findIdByNombreGrupo(nombreGrupo);
	}
}
