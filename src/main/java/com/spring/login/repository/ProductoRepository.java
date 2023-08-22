package com.spring.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.login.Constants.ApiConstants;
import com.spring.login.models.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
	
	@Query(value = "SELECT p.id_producto, p.nombre, p.precio, p.id_productogrupo, pg.nombre_grupo, p.descripcion, "
            + "p.foto, p.stock FROM productos p join productos_grupo pg "
            + "on p.id_productogrupo = pg.id_productogrupo where pg.nombre_grupo = :grupo ORDER BY id_producto;", 
            nativeQuery = true)
	List<Producto> findProductsByGroup(@Param("grupo") String grupo);
		
	@Query(value = "SELECT p.id_producto, p.nombre, p.precio, p.id_productoGrupo, pg.nombre_grupo, p.descripcion, "
			+ "p.foto, p.stock FROM productos p join productos_grupo pg on p.id_productoGrupo = pg.id_productoGrupo "
			+ "where p.nombre like %:query% "
            + "ORDER BY id_producto;", 
            nativeQuery = true)
	List<Producto> findProductsByQuerySearch(@Param("query") String query);

}
