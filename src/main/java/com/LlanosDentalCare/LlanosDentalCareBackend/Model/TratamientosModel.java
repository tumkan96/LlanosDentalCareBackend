package com.LlanosDentalCare.LlanosDentalCareBackend.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tratamientos")
public class TratamientosModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tratamiento")
	private int id_tratamiento;

	@Column(name = "nombre_tratamiento")
	private String nombre_tratamiento;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "precio")
	private double precio;

	@ManyToOne
	@JoinColumn(name = "id_especialidad", referencedColumnName = "id_especialidad")
	private EspecialidadesModel especialidad;

	public int getId_tratamiento() {
		return id_tratamiento;
	}

	public void setId_tratamiento(int id_tratamiento) {
		this.id_tratamiento = id_tratamiento;
	}

	public String getNombre_tratamiento() {
		return nombre_tratamiento;
	}

	public void setNombre_tratamiento(String nombre_tratamiento) {
		this.nombre_tratamiento = nombre_tratamiento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public EspecialidadesModel getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(EspecialidadesModel especialidad) {
		this.especialidad = especialidad;
	}

}
