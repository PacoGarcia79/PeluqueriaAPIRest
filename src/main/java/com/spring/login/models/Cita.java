package com.spring.login.models;

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
@NamedQuery(name="Cita.findAll", query="SELECT c FROM Cita c")
public class Cita implements Serializable {
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
	private HorarioEmpleado horariosempleado;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private User user;

	public Cita() {
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

	public HorarioEmpleado getHorariosempleado() {
		return this.horariosempleado;
	}

	public void setHorariosempleado(HorarioEmpleado horariosempleado) {
		this.horariosempleado = horariosempleado;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}