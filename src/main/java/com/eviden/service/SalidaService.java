package com.eviden.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.eviden.model.Salida;
import com.eviden.repository.SalidaRepository;

@Service
public class SalidaService {
	
	@Autowired
	SalidaRepository salidaRepository;
	
	public Page<Salida> getSalidas(int pageNum, int pageSize, String sortField, boolean order){
		Pageable pageable;
		if(order) {
			pageable = PageRequest.of(pageNum -1, pageSize, Sort.by(sortField).ascending());
		}else {
			pageable = PageRequest.of(pageNum -1, pageSize, Sort.by(sortField).descending());
		}
		return salidaRepository.findAll(pageable);
	}
	
	public Salida save(Salida salida) {
		return salidaRepository.save(salida);
	}
	
	public List<Salida> getAll(){
		return salidaRepository.findAll();
	}
	
	public Salida getSalida(int id){
		return salidaRepository.findById(id).orElse(null);
	}
	
	public void delete(Salida salida) {
		salidaRepository.delete(salida);
	}

}
