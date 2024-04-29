package com.eviden.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eviden.model.Barco;



public interface BarcoRepository extends JpaRepository<Barco, Integer>{
	
	
	@Query(value= "SELECT * FROM Barco WHERE numero_matricula = ?1", nativeQuery = true)
	List<Barco> findByNumero_matricula(String numero_matricula);
	
	@Query(value= "SELECT * FROM Barco WHERE numero_amarre = ?1", nativeQuery = true)
	List<Barco> findByNumero_amarre(int numero_amarre);
	
	List<Barco> findByNombreContaining(String nombre);
	

}
