package com.eviden.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eviden.exception.GlobalException;
import com.eviden.model.Socio;
import com.eviden.repository.SocioRepository;

@Service
public class SocioService implements UserDetailsService{
	
	@Autowired
	SocioRepository socioRepository;
	
	public List<Socio> getAll(){
		return socioRepository.findAll();
	}
	
	public Socio getSocio(int id) {
		return socioRepository.findById(id).orElse(null);
	}
	
	public Socio save(Socio socioAdd) {
		return socioRepository.save(socioAdd);
	}
	
	public List<Socio> getSocioByDni(String dni){
		return socioRepository.findByDni(dni);
	}
	
	public void delSocio(Socio socioDel) {
		socioRepository.delete(socioDel);
	}

	@Override
	public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
		List<Socio> result = socioRepository.findByDni(dni);
		if(result != null && result.size()==1) {
			return result.get(0);
		}else {
			throw new GlobalException("usuario no encontrado"+dni);
		}
	}

}
