package com.eviden.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Socio implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_socio;

	@NotBlank(message = "El nombre no puede estar vacio")
	private String nombre;
	@NotBlank(message = "El apellido no puede estar vacio")
	private String apellido;
	
	@Pattern(regexp = "[0-9]{8}[A-Z]", message = "Debes de cumplir el formato de DNI (12345678A)")
	private String dni;

	
	@OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL)
	private List<Barco> barcos;

	public Socio() {
		super();
	}

	public Socio(int id_socio, String nombre, String apellido, String dni, List<Barco> barcos) {
		super();
		this.id_socio = id_socio;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.barcos = barcos;
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

	public List<Barco> getBarcos() {
		return barcos;
	}

	public void setBarcos(List<Barco> barcos) {
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
		Socio other = (Socio) obj;
		return id_socio == other.id_socio;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> autorities = new ArrayList<>();
		if(this.barcos.size()>5) {
			autorities.add(new SimpleGrantedAuthority("admin"));
		}else {
			autorities.add(new SimpleGrantedAuthority("user"));
		}
		return null;
	}

	@Override
	public String getPassword() {
		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		
		return enc.encode(this.dni);
	}

	@Override
	public String getUsername() {
		
		return this.dni;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
