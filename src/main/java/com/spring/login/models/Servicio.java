package com.spring.login.models;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the servicios database table.
 * 
 */
@Entity
@Table(name="servicios")
@NamedQuery(name="Servicio.findAll", query="SELECT s FROM Servicio s")
public class Servicio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_servicio")
	private Long idServicio;

	@Lob
	private String foto;

	private String nombre;

	private BigDecimal precio;

	public Servicio() {
	}

	public Long getIdServicio() {
		return this.idServicio;
	}

	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}

	public String getFoto() {
		return this.foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

}