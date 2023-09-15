package com.spring.peluqueria.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the citas database table.
 * 
 */
@Entity
@Table(name="citas")
public class Appointment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_cita")
	private Long idCita;

	private byte cancelada;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	//bi-directional many-to-one association to Horariosempleado
	@ManyToOne
	@JoinColumn(name="id_horarioempleado")
	private EmployeeSchedule horariosempleado;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private User user;

	public Appointment() {
	}

	public Long getIdCita() {
		return this.idCita;
	}

	public void setIdCita(Long idCita) {
		this.idCita = idCita;
	}

	public byte getCancelada() {
		return this.cancelada;
	}

	public void setCancelada(byte cancelada) {
		this.cancelada = cancelada;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public EmployeeSchedule getHorariosempleado() {
		return this.horariosempleado;
	}

	public void setHorariosempleado(EmployeeSchedule horariosempleado) {
		this.horariosempleado = horariosempleado;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}