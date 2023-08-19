package com.spring.login.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the disponibilidad database table.
 * 
 */
@Entity
@NamedQuery(name="Disponibilidad.findAll", query="SELECT d FROM Disponibilidad d")
public class Disponibilidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_disponibilidad")
	private Long idDisponibilidad;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_comienzo_no_disponible")
	private Date fechaComienzoNoDisponible;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_fin_no_disponible")
	private Date fechaFinNoDisponible;

	//bi-directional many-to-one association to Horariosempleado
	@ManyToOne
	@JoinColumn(name="id_horarioempleado")
	private HorarioEmpleado horariosempleado;

	public Disponibilidad() {
	}

	public Long getIdDisponibilidad() {
		return this.idDisponibilidad;
	}

	public void setIdDisponibilidad(Long idDisponibilidad) {
		this.idDisponibilidad = idDisponibilidad;
	}

	public Date getFechaComienzoNoDisponible() {
		return this.fechaComienzoNoDisponible;
	}

	public void setFechaComienzoNoDisponible(Date fechaComienzoNoDisponible) {
		this.fechaComienzoNoDisponible = fechaComienzoNoDisponible;
	}

	public Date getFechaFinNoDisponible() {
		return this.fechaFinNoDisponible;
	}

	public void setFechaFinNoDisponible(Date fechaFinNoDisponible) {
		this.fechaFinNoDisponible = fechaFinNoDisponible;
	}

	public HorarioEmpleado getHorariosempleado() {
		return this.horariosempleado;
	}

	public void setHorariosempleado(HorarioEmpleado horariosempleado) {
		this.horariosempleado = horariosempleado;
	}

}