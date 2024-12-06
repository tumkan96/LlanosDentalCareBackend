package com.LlanosDentalCare.LlanosDentalCareBackend.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "odontologos")
public class OdontologosModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_odontologo")
	private Long id_odontologo;

	@Column(name = "numero_licencia")
	private String numeroLicencia;

	@Column(name = "universidad_graduado")
	private String universidadGraduado;

	@Column(name = "matricula")
	private String matricula;

	@Column(name = "direccion")
	private String direccion;

	@OneToOne
	@JoinColumn(name = "id_persona", referencedColumnName = "id_persona", unique = true)
	private PersonasModel persona;

	public Long getId_odontologo() {
		return id_odontologo;
	}

	public void setId_odontologo(Long id_odontologo) {
		this.id_odontologo = id_odontologo;
	}

	public String getNumeroLicencia() {
		return numeroLicencia;
	}

	public void setNumeroLicencia(String numeroLicencia) {
		this.numeroLicencia = numeroLicencia;
	}

	public String getUniversidadGraduado() {
		return universidadGraduado;
	}

	public void setUniversidadGraduado(String universidadGraduado) {
		this.universidadGraduado = universidadGraduado;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public PersonasModel getPersona() {
		return persona;
	}

	public void setPersona(PersonasModel persona) {
		this.persona = persona;
	}

}
