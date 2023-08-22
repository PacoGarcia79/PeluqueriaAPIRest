package com.spring.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.login.models.ProductosGrupo;

public interface ProductosGrupoRepository extends JpaRepository<ProductosGrupo, Long>{

	
	@Query(value = "SELECT id_productogrupo from productos_grupo where "
			+ "nombre_grupo = :nombreGrupo", 
            nativeQuery = true)
	Long findIdByNombreGrupo(@Param("nombreGrupo") String nombreGrupo);
}
