package com.spring.peluqueria.models;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the disponibilidad database table.
 * 
 */
@Entity
@Table(name="disponibilidad")
public class Availability implements Serializable {
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
	private EmployeeSchedule horariosempleado;
	
	@Column(name="id")
	private Long idUsuario;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="id_horario")
	private Long idHorario;
	
	@Column(name="hora")
	private Time hora;

	public Availability() {
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

	public EmployeeSchedule getHorariosempleado() {
		return this.horariosempleado;
	}

	public void setHorariosempleado(EmployeeSchedule horariosempleado) {
		this.horariosempleado = horariosempleado;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdHorario() {
		return idHorario;
	}

	public void setIdHorario(Long idHorario) {
		this.idHorario = idHorario;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}
	
	

}