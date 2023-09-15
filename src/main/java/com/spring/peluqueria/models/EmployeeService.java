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
public class EmployeeService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_servicioempleado")
	private Long idServicioempleado;

	//bi-directional many-to-one association to Servicio
	@ManyToOne
	@JoinColumn(name="id_servicio")
	private Service servicio;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_empleado")
	private User user;

	public EmployeeService() {
	}

	public Long getIdServicioempleado() {
		return this.idServicioempleado;
	}

	public void setIdServicioempleado(Long idServicioempleado) {
		this.idServicioempleado = idServicioempleado;
	}

	public Service getServicio() {
		return this.servicio;
	}

	public void setServicio(Service servicio) {
		this.servicio = servicio;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}