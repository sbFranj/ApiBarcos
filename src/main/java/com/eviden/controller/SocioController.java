package com.eviden.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eviden.exception.GlobalException;
import com.eviden.model.Socio;
import com.eviden.service.SocioService;

@RestController
public class SocioController {
	
	@Autowired
	SocioService socioService;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@GetMapping("/socios")
	public ResponseEntity<?> getSocios() {
		
		return ResponseEntity.ok(socioService.getAll());
	}
	
	
	@GetMapping("/socios/{idSocio}")
	public ResponseEntity<?> getSocio(@PathVariable String idSocio) {
		Socio find = null;
		try {
			find = socioService.getSocio(Integer.parseInt(idSocio));
		} catch (Exception e) {
			return new ResponseEntity<String>("socio no encontrado", HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(find);
	}
	
	
	@PostMapping("/socios/add_socio")
	public ResponseEntity<?> postSocio(@RequestBody Socio socioAdd) {
		Socio newSocio = null;
			if(!socioService.getSocioByDni(socioAdd.getDni()).isEmpty()) {
				return new ResponseEntity<String>("Dni existente", HttpStatus.NOT_FOUND);
			}else {
				try {
					newSocio = socioService.save(socioAdd);
				} catch (Exception e) {
					return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
				}
			}
		
		return ResponseEntity.ok(newSocio);
	}
	
	
	@DeleteMapping("/socios/del_socio/{idSocio}")
	public ResponseEntity<?> delSocio(@PathVariable String idSocio) {
		
		try {			
			Socio socioDel = socioService.getSocio(Integer.parseInt(idSocio));
			socioService.delSocio(socioDel);
		} catch (Exception e) {
			return new ResponseEntity<String>("El socio no existe", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(null);
	}
	
	
	@PutMapping("/socios/edit_socio/{idSocio}")
	public ResponseEntity<?> editSocio(@RequestBody Socio socioAdd, @PathVariable String idSocio) {
		Socio find = null;
		try {
			find = socioService.getSocio(Integer.parseInt(idSocio));
			if(socioService.getSocioByDni(socioAdd.getDni()).isEmpty() || find.getId_socio()==Integer.parseInt(idSocio)) {
				try {
					socioAdd.setId_socio(find.getId_socio());
					find = socioService.save(socioAdd);
				} catch (Exception e) {
					throw new GlobalException(e.getMessage());
				}
			}else {
				throw new GlobalException("dni no existe");
			}
		} catch (Exception e) {
			throw new GlobalException(e.getMessage());
		}
		
		return ResponseEntity.ok(find);
	}
	
	@GetMapping("/socios/dni/{dni}")
	public ResponseEntity<?> existeNumeroamarre(@PathVariable String dni){
		List<Socio> find = null;
		try {
			find = socioService.getSocioByDni(dni);
					
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		
		return ResponseEntity.ok(find);
	}
	

}
