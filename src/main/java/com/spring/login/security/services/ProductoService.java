package com.spring.login.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.login.models.Producto;
import com.spring.login.repository.ProductoRepository;

@Service
public class ProductoService {
	
	@Autowired
	ProductoRepository productoRepository;

	public List<Producto> findAll(){
		return productoRepository.findAll();
	}
	
	public List<Producto> findProductsByGroup(String grupo){
		return productoRepository.findProductsByGroup(grupo);
	}

	public Producto save(Producto producto) {
		return productoRepository.save(producto);
	}
	
	public Optional<Producto> findById(Long id) {
		return productoRepository.findById(id);
	}
	
	public void deleteById(Long id) {
		productoRepository.deleteById(id);
	}
	
	public List<Producto> findProductsByQuerySearch(String query){
		return productoRepository.findProductsByQuerySearch(query);
	}
	
	
}
