package com.eviden.model;

import java.util.Date;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class Salida {

	@Id
	@Column(name="id_salida")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_barco")
	@NotNull(message = "Elige un barco")
	private Barco barco;

	@FutureOrPresent(message = "La fecha no puede ser anterior a hoy")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Column(name="fecha_hora_salida")
	private Date fecha;

	@NotBlank(message = "Escribe un destino")
	private String destino;

	@Pattern(regexp = "[0-9]{8}[A-Z]", message = "Debes de cumplir el formato de DNI (12345678A)")
	@Column(name="dni_patron")
	private String dni;
                                           
	public Salida() {
		super();
	}

	public Salida(int id_salida, Barco barco, Date fecha_hora_salida, String destino, String dni_patron) {
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
		Salida other = (Salida) obj;
		return id == other.id;
	}

}
