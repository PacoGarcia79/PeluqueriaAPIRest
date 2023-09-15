package com.spring.login.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the horariosempleados database table.
 * 
 */
@Entity
@Table(name="horariosempleados")
public class EmployeeSchedule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_horarioempleado")
	private Long idHorarioempleado;

	//bi-directional many-to-one association to Horario
	@ManyToOne
	@JoinColumn(name="id_horario")
	private Schedule horario;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_empleado")
	private User user;

	public EmployeeSchedule() {
	}

	public Long getIdHorarioempleado() {
		return this.idHorarioempleado;
	}

	public void setIdHorarioempleado(Long idHorarioempleado) {
		this.idHorarioempleado = idHorarioempleado;
	}

	public Schedule getHorario() {
		return this.horario;
	}

	public void setHorario(Schedule horario) {
		this.horario = horario;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

}