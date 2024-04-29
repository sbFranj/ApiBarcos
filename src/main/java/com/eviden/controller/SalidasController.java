package com.eviden.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eviden.exception.GlobalException;
import com.eviden.model.Barco;
import com.eviden.model.Salida;
import com.eviden.model.SalidaDTO;
import com.eviden.service.BarcoService;
import com.eviden.service.SalidaService;
@RestController
public class SalidasController {
	
	@Autowired
	SalidaService salidaService;
	
	@Autowired
	BarcoService barcoService;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@GetMapping("/salidas")
	public ResponseEntity<?> getAll() {
		List<SalidaDTO> salidas = salidaService.getAll().stream().map(salida-> 
								new SalidaDTO(salida.getId(), 
										salida.getBarco(), 
										salida.getFecha(), 
										salida.getDestino(), 
										salida.getDni())).toList();
		
		return ResponseEntity.ok(salidas);
	}
	
	
	@PostMapping("/salidas/add_salida/{idBarco}")
	public ResponseEntity<?> addSalida(@RequestBody SalidaDTO salidaAdd, @PathVariable String idBarco) {
		SalidaDTO result = null;
		try {
			Barco find = barcoService.getBarco(Integer.valueOf(idBarco));
			try {
				salidaAdd.setBarco(find);
				Salida save = salidaService.save(modelMapper.map(salidaAdd, Salida.class));
				result = modelMapper.map(save, SalidaDTO.class);
			} catch (Exception e) {
				throw new GlobalException(e.getMessage());
			}
		} catch (Exception e) {
			throw new GlobalException(e.getMessage());
		}
		
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/salidas/{idSalida}")
	public ResponseEntity<?> getSalida(@PathVariable String idSalida) {
		SalidaDTO result = null;
		try {
			Salida salida = salidaService.getSalida(Integer.parseInt(idSalida));
			result = modelMapper.map(salida, SalidaDTO.class);
		} catch (Exception e) {
			return new ResponseEntity<String>("Salida no encontrada", HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(result);
	}
	
	
	@DeleteMapping("/salidas/del_salida/{idSalida}")
	public ResponseEntity<?> delSalida(@PathVariable String idSalida) {
		try {
			Salida salida = salidaService.getSalida(Integer.parseInt(idSalida));
			salidaService.delete(salida);
		} catch (Exception e) {
			return new ResponseEntity<String>("Salida no encontrada", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(null);
	}
	
	
	@PutMapping("/salidas/edit_salida/{idSalida}/{idBarco}")
	public ResponseEntity<?> editSalida(@RequestBody SalidaDTO salidaEdit, @PathVariable String idSalida, @PathVariable String idBarco) {
		SalidaDTO result = null;
		try {
			Salida find = salidaService.getSalida(Integer.parseInt(idSalida));
			try {
				
				salidaEdit.setId(find.getId());
				salidaEdit.setBarco(barcoService.getBarco(Integer.parseInt(idBarco)));
				
				Salida save = salidaService.save(modelMapper.map(salidaEdit, Salida.class));
				result = modelMapper.map(save, SalidaDTO.class);
			} catch (Exception e) {
				throw new GlobalException(e.getMessage());
			}
		} catch (Exception e) {
			throw new GlobalException(e.getMessage());
		}
		
		return ResponseEntity.ok(result);
	}

}
