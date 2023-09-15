package com.spring.peluqueria.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the productoscitas database table.
 * 
 */
@Entity
@Table(name="productoscitas")
public class AppointmentProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_productocita")
	private Long idProductocita;

	private int cantidad;

	//bi-directional many-to-one association to Cita
	@ManyToOne
	@JoinColumn(name="id_cita")
	private Appointment cita;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Product producto;

	public AppointmentProduct() {
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

	public Appointment getCita() {
		return this.cita;
	}

	public void setCita(Appointment cita) {
		this.cita = cita;
	}

	public Product getProducto() {
		return this.producto;
	}

	public void setProducto(Product producto) {
		this.producto = producto;
	}

}