package com.spring.login.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the servicioscitas database table.
 * 
 */
@Entity
@Table(name="servicioscitas")
@NamedQuery(name="Servicioscita.findAll", query="SELECT s FROM ServicioCita s")
public class ServicioCita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_serviciocita")
	private Long idServiciocita;

	//bi-directional many-to-one association to Cita
	@ManyToOne
	@JoinColumn(name="id_cita")
	private Cita cita;

	//bi-directional many-to-one association to Serviciosempleado
	@ManyToOne
	@JoinColumn(name="id_servicioempleado")
	private ServicioEmpleado serviciosempleado;

	public ServicioCita() {
	}

	public Long getIdServiciocita() {
		return this.idServiciocita;
	}

	public void setIdServiciocita(Long idServiciocita) {
		this.idServiciocita = idServiciocita;
	}

	public Cita getCita() {
		return this.cita;
	}

	public void setCita(Cita cita) {
		this.cita = cita;
	}

	public ServicioEmpleado getServiciosempleado() {
		return this.serviciosempleado;
	}

	public void setServiciosempleado(ServicioEmpleado serviciosempleado) {
		this.serviciosempleado = serviciosempleado;
	}

}