package com.spring.login.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the productoscitas database table.
 * 
 */
@Entity
@Table(name="productoscitas")
@NamedQuery(name="Productoscita.findAll", query="SELECT p FROM ProductoCita p")
public class ProductoCita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_productocita")
	private Long idProductocita;

	private int cantidad;

	//bi-directional many-to-one association to Cita
	@ManyToOne
	@JoinColumn(name="id_cita")
	private Cita cita;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;

	public ProductoCita() {
	}

	public Long getIdProductocita() {
		return this.idProductocita;
	}

	public void setIdProductocita(Long idProductocita) {
		this.idProductocita = idProductocita;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Cita getCita() {
		return this.cita;
	}

	public void setCita(Cita cita) {
		this.cita = cita;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}