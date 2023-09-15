package com.spring.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.login.models.ProductGroup;

public interface ProductGroupRepository extends JpaRepository<ProductGroup, Long>{

	
	@Query(value = "SELECT id_productogrupo from productos_grupo where "
			+ "nombre_grupo = :nombreGrupo", 
            nativeQuery = true)
	Long findIdByGroupName(@Param("nombreGrupo") String nombreGrupo);
}
