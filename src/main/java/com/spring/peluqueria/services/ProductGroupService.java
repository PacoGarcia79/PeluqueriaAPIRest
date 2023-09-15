package com.spring.login.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.login.models.ProductGroup;
import com.spring.login.repository.ProductGroupRepository;

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
