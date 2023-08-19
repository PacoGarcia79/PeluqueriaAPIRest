package com.spring.login.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the productosgrupo database table.
 * 
 */
@Entity
@NamedQuery(name="Productosgrupo.findAll", query="SELECT p FROM ProductosGrupo p")
public class ProductosGrupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_productogrupo")
	private Long idProductogrupo;

	@Lob
	private String foto;

	@Column(name="nombre_grupo")
	private String nombreGrupo;

	public ProductosGrupo() {
	}

	public Long getIdProductogrupo() {
		return this.idProductogrupo;
	}

	public void setIdProductogrupo(Long idProductogrupo) {
		this.idProductogrupo = idProductogrupo;
	}

	public String getFoto() {
		return this.foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getNombreGrupo() {
		return this.nombreGrupo;
	}

	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}
}