package com.eviden.model;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Barco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_barco;

	@NotBlank(message = "La matricula no puede estar vacia")
	private String numero_matricula;

	@NotBlank(message = "El nombre no puede estar vacio")
	private String nombre;

	@Min(value = 0, message = "El numero de amarre no puede ser un numero negatitvo")
	private int numero_amarre;

	@Min(value = 0, message = "La cuota no puede ser un numero negatitvo")
	private double cuota;

	
	@OneToMany(mappedBy = "barco", cascade = CascadeType.ALL)
	private List<Salida> salidas;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_propietario")
	@NotNull(message = "Elige un propietario")
	private Socio propietario;

	public Barco() {
		super();
	}

	public Barco(int id_barco, String numero_matricula, String nombre, int numero_amarre, double cuota,
			Socio propietario) {
		super();
		this.id_barco = id_barco;
		this.numero_matricula = numero_matricula;
		this.nombre = nombre;
		this.numero_amarre = numero_amarre;
		this.cuota = cuota;
		this.propietario = propietario;
	}

	public int getId_barco() {
		return id_barco;
	}

	public void setId_barco(int id_barco) {
		this.id_barco = id_barco;
	}

	public String getNumero_matricula() {
		return numero_matricula;
	}

	public void setNumero_matricula(String numero_matricula) {
		this.numero_matricula = numero_matricula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumero_amarre() {
		return numero_amarre;
	}

	public void setNumero_amarre(int numero_amarre) {
		this.numero_amarre = numero_amarre;
	}

	public double getCuota() {
		return cuota;
	}

	public void setCuota(double cuota) {
		this.cuota = cuota;
	}

	public List<Salida> getSalidas() {
		return salidas;
	}

	public void setSalidas(List<Salida> salidas) {
		this.salidas = salidas;
	}

	public Socio getPropietario() {
		return propietario;
	}

	public void setPropietario(Socio propietario) {
		this.propietario = propietario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_barco);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Barco other = (Barco) obj;
		return id_barco == other.id_barco;
	}

}
