package com.spring.login.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the serviciosempleados database table.
 * 
 */
@Entity
@Table(name="serviciosempleados")
@NamedQuery(name="Serviciosempleado.findAll", query="SELECT s FROM ServicioEmpleado s")
public class ServicioEmpleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_servicioempleado")
	private Long idServicioempleado;

	//bi-directional many-to-one association to Servicio
	@ManyToOne
	@JoinColumn(name="id_servicio")
	private Servicio servicio;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_empleado")
	private User user;

	public ServicioEmpleado() {
	}

	public Long getIdServicioempleado() {
		return this.idServicioempleado;
	}

	public void setIdServicioempleado(Long idServicioempleado) {
		this.idServicioempleado = idServicioempleado;
	}

	public Servicio getServicio() {
		return this.servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}