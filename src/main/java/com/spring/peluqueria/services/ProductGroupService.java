package com.spring.peluqueria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.peluqueria.models.ProductGroup;
import com.spring.peluqueria.repository.ProductGroupRepository;

@Service
public class ProductGroupService {

	@Autowired
	ProductGroupRepository productsGroupRepository;
	
	public List<ProductGroup> findAll(){
		return productsGroupRepository.findAll();
	}
	
	public ProductGroup save(ProductGroup productsGroup) {
		return productsGroupRepository.save(productsGroup);
	}
	
	public Long findIdByNombreGrupo(String groupName) {
		return productsGroupRepository.findIdByGroupName(groupName);
	}
}
