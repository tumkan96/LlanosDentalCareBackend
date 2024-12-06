package com.LlanosDentalCare.LlanosDentalCareBackend.Model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "especialidad")
public class EspecialidadesModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_especialidad")
	private int id_especialidad;
	
	@Column(name = "nombre_especialidad")
	private String nombre_especialidad;

	@Column(name = "descripcion")
	private String descripcion;

	public int getId_especialidad() {
		return id_especialidad;
	}

	public void setId_especialidad(int id_especialidad) {
		this.id_especialidad = id_especialidad;
	}

	public String getNombre_especialidad() {
		return nombre_especialidad;
	}

	public void setNombre_especialidad(String nombre_especialidad) {
		this.nombre_especialidad = nombre_especialidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

}
