package com.spring.login.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.login.models.Product;
import com.spring.login.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productoRepository;

	public List<Product> findAll(){
		return productoRepository.findAll();
	}
	
	public List<Product> findProductsByGroup(String grupo){
		return productoRepository.findProductsByGroup(grupo);
	}

	public Product save(Product producto) {
		return productoRepository.save(producto);
	}
	
	public Optional<Product> findById(Long id) {
		return productoRepository.findById(id);
	}
	
	public void deleteById(Long id) {
		productoRepository.deleteById(id);
	}
	
	public List<Product> findProductsByQuerySearch(String query){
		return productoRepository.findProductsByQuerySearch(query);
	}
	
	
}
