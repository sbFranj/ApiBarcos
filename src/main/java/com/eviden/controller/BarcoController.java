package com.eviden.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eviden.model.Barco;
import com.eviden.model.BarcoDTO;
import com.eviden.model.Socio;
import com.eviden.service.BarcoService;
import com.eviden.service.SocioService;

@RestController
public class BarcoController {
	
	@Autowired
	BarcoService barcoService;
		
	@Autowired
	SocioService socioService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping("/barcos")
	public ResponseEntity<?> getAll() {
		List<Barco> barcos = barcoService.getAll();
		
			
		return ResponseEntity.ok(barcos);
	}
	
	@GetMapping("/barcos/propietario/{idBarco}")
	public ResponseEntity<?> getPropietario(@PathVariable String idBarco) {

		Barco find = barcoService.getBarco(Integer.parseInt(idBarco));
		if(find==null) {
			return new ResponseEntity<String>("Barco no encontrado", HttpStatus.NOT_FOUND);
		}
		find.getPropietario().setDni("");
		
		return ResponseEntity.ok(find.getPropietario());
	}
	
	@GetMapping("/barcos/{idBarco}")
	public ResponseEntity<?> getBarco(@PathVariable String idBarco) {
		
		Barco find = barcoService.getBarco(Integer.parseInt(idBarco));
		if(find==null) {
			return new ResponseEntity<String>("Barco no encontrado", HttpStatus.NOT_FOUND);
		}
		find.getPropietario().setDni("");
		
		return ResponseEntity.ok(find);
	}
	
	
	@GetMapping("/barcos/busqueda")
	public ResponseEntity<?> search(@RequestParam String nombre){
		List<Barco> search = barcoService.search(nombre);
		
		return ResponseEntity.ok(search);
	}
	

	@PostMapping("/barcos/add_barco/{idSocio}")
	public ResponseEntity<?> postBarco(@RequestBody BarcoDTO barcoAdd, @PathVariable String idSocio) {
		
		
		Barco saved = barcoService.getByMatricula(barcoAdd.getNumero_matricula()).isEmpty()?
						null: barcoService.getByMatricula(barcoAdd.getNumero_matricula()).get(0);
		
		
		if(saved==null && barcoService.getByNumeroAmarre(barcoAdd.getNumero_amarre()).isEmpty()) {
			try {
				barcoAdd.setPropietario(socioService.getSocio(Integer.valueOf(idSocio)));
				saved = barcoService.save(modelMapper.map(barcoAdd, Barco.class));
				saved.getPropietario().setDni("");
			} catch (Exception e) {
				String m = e.getMessage();
				return new ResponseEntity<String>(m, HttpStatus.NOT_ACCEPTABLE);
			}
		}else if(!barcoService.getByNumeroAmarre(barcoAdd.getNumero_amarre()).isEmpty()) {
			return new ResponseEntity<String>("numero de amarre existe", HttpStatus.NOT_ACCEPTABLE);
		}else {
			return new ResponseEntity<String>("matricula existe", HttpStatus.NOT_ACCEPTABLE);
		}
		
		return ResponseEntity.ok(saved);
	}
	
	
	@DeleteMapping("/barcos/del_barco/{idBarco}")
	public ResponseEntity<Map<String,String>> delBarco(@PathVariable String idBarco) {
		try {
			Barco find = barcoService.getBarco(Integer.parseInt(idBarco));
			barcoService.delBarco(find);
		} catch (Exception e) {
			Map<String, String> resp2 = new HashMap<>();
			resp2.put("message", "El baro no existe");
			new ResponseEntity<>(resp2, HttpStatus.OK);
		}
		Map<String, String> resp = new HashMap<>();
		resp.put("message", "eliminado");
		
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	
	@PutMapping("/barcos/edit_barco/{idBarco}")
	public ResponseEntity<?> editBarco(@RequestBody BarcoDTO barcoEdit, @PathVariable String idBarco, BindingResult binding) {
		Barco find = barcoService.getByMatricula(barcoEdit.getNumero_matricula()).isEmpty()?
				null: barcoService.getByMatricula(barcoEdit.getNumero_matricula()).get(0);
		int id = -1;
		try {
			id = Integer.parseInt(idBarco);
		} catch (Exception e) {
			return new ResponseEntity<String>("Barco no encontrado", HttpStatus.NOT_FOUND);
		}
		if(!barcoService.getByNumeroAmarre(barcoEdit.getNumero_amarre()).isEmpty() && 
				barcoService.getByNumeroAmarre(barcoEdit.getNumero_amarre()).get(0).getId_barco()!=id) {
			return new ResponseEntity<String>("numero de amarre asigando", HttpStatus.NOT_ACCEPTABLE);

		}else if(find!=null && find.getId_barco()!=id){
			return new ResponseEntity<String>("matricula existe", HttpStatus.NOT_ACCEPTABLE);

		}else {
				try {
					
				if(barcoEdit.getPropietario()==null) {
					Socio s = barcoService.getBarco(id).getPropietario();
					barcoEdit.setPropietario(s);
				}
				barcoEdit.setId_barco(id);
				Barco edit = barcoService.save(modelMapper.map(barcoEdit, Barco.class));
				find = edit;
				find.getPropietario().setDni("");
			} catch (Exception e) {
				String m = e.getMessage();
				return new ResponseEntity<String>(m, HttpStatus.NOT_ACCEPTABLE);
			}
		}
		
		return ResponseEntity.ok(find);
	}
	
	@GetMapping("/barcos/matricula/{matricula}")
	public ResponseEntity<?> existeMatricula(@PathVariable String matricula){
		List<Barco> find = barcoService.getByMatricula(matricula);
					
		
		return ResponseEntity.ok(find);
	}
	
	@GetMapping("/barcos/amarre/{numero}")
	public ResponseEntity<?> existeNumeroamarre(@PathVariable String numero){
		List<Barco> find = null;
		try {
			find = barcoService.getByNumeroAmarre(Integer.parseInt(numero));
					
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		
		return ResponseEntity.ok(find);
	}
	
	

}
