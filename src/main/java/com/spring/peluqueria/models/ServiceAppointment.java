package com.spring.login.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the servicioscitas database table.
 * 
 */
@Entity
@Table(name="servicioscitas")
public class ServiceAppointment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_serviciocita")
	private Long idServiciocita;

	//bi-directional many-to-one association to Cita
	@ManyToOne
	@JoinColumn(name="id_cita")
	private Appointment cita;

	//bi-directional many-to-one association to Serviciosempleado
	@ManyToOne
	@JoinColumn(name="id_servicioempleado")
	private EmployeeService serviciosempleado;

	public ServiceAppointment() {
	}

	public Long getIdServiciocita() {
		return this.idServiciocita;
	}

	public void setIdServiciocita(Long idServiciocita) {
		this.idServiciocita = idServiciocita;
	}

	public Appointment getCita() {
		return this.cita;
	}

	public void setCita(Appointment cita) {
		this.cita = cita;
	}

	public EmployeeService getServiciosempleado() {
		return this.serviciosempleado;
	}

	public void setServiciosempleado(EmployeeService serviciosempleado) {
		this.serviciosempleado = serviciosempleado;
	}

}