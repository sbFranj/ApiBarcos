package com.eviden.model;

import java.util.List;
import java.util.Objects;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public class SocioDTO {

	private int id_socio;

	private String nombre;
	
	private String apellido;

	private String dni;

	private List<BarcoDTO> barcos;

	public SocioDTO() {
		super();
	}

	public SocioDTO(int id_socio, String nombre, String apellido, String dni) {
		super();
		this.id_socio = id_socio;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
	}

	public int getId_socio() {
		return id_socio;
	}

	public void setId_socio(int id_socio) {
		this.id_socio = id_socio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public List<BarcoDTO> getBarcos() {
		return barcos;
	}

	public void setBarcos(List<BarcoDTO> barcos) {
		this.barcos = barcos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_socio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SocioDTO other = (SocioDTO) obj;
		return id_socio == other.id_socio;
	}

}
