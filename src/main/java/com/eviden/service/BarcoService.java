package com.eviden.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eviden.model.Barco;
import com.eviden.model.BarcoDTO;
import com.eviden.repository.BarcoRepository;

@Service
public class BarcoService {
	
	@Autowired
	BarcoRepository barcoRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public List<Barco> getAll(){
		return barcoRepository.findAll();
	}
		
	public List<Barco> getByMatricula(String matricula){
		return barcoRepository.findByNumero_matricula(matricula);
	}
	
	public List<Barco> getByNumeroAmarre(int numeroAmarre){
		return barcoRepository.findByNumero_amarre(numeroAmarre);
	}
	
	public Barco getBarco(int id) {
		return barcoRepository.findById(id).orElse(null);
	}
	
	public List<Barco> search(String nombre){
		return barcoRepository.findByNombreContaining(nombre);
	}
	
	public Barco save(Barco barco) {
		return barcoRepository.save(barco);
	}
	
	public void delBarco(Barco barco) {
		barcoRepository.delete(barco);
	}

}
