package com.spring.login.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.login.models.Product;
import com.spring.login.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;

	public List<Product> findAll(){
		return productRepository.findAll();
	}
	
	public List<Product> findProductsByGroup(String group){
		return productRepository.findProductsByGroup(group);
	}

	public Product save(Product producto) {
		return productRepository.save(producto);
	}
	
	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}
	
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}
	
	public List<Product> findProductsByQuerySearch(String query){
		return productRepository.findProductsByQuerySearch(query);
	}
	
	
}
