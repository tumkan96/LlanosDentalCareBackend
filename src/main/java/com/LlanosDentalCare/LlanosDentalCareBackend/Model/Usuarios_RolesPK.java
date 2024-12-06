package com.LlanosDentalCare.LlanosDentalCareBackend.Model;

import java.io.Serializable;
import java.util.Objects;

public class Usuarios_RolesPK implements Serializable {
	protected String usuario;
	protected int id_rol;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getId_rol() {
		return id_rol;
	}

	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_rol, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuarios_RolesPK other = (Usuarios_RolesPK) obj;
		return id_rol == other.id_rol && Objects.equals(usuario, other.usuario);
	}

}
