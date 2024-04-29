package com.eviden.model;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;


public class SalidaDTO {

	
	private int id;

	private Barco barco;

	private Date fecha;

	private String destino;

	private String dni;

	public SalidaDTO() {
		super();
	}

	public SalidaDTO(int id_salida, Barco barco, Date fecha_hora_salida, String destino, String dni_patron) {
		super();
		this.id = id_salida;
		this.barco = barco;
		this.fecha = fecha_hora_salida;
		this.destino = destino;
		this.dni = dni_patron;
	}
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Barco getBarco() {
		return barco;
	}

	public void setBarco(Barco barco) {
		this.barco = barco;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalidaDTO other = (SalidaDTO) obj;
		return id == other.id;
	}

}
