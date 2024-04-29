package com.eviden.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eviden.model.Socio;
import java.util.List;


public interface SocioRepository extends JpaRepository<Socio, Integer>{
	
	List<Socio> findByDni(String dni);

}
