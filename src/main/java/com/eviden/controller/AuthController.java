package com.eviden.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eviden.exception.GlobalException;
import com.eviden.model.Socio;
import com.eviden.model.TokenDTO;
import com.eviden.model.UserLogin;
import com.eviden.service.SocioService;
import com.eviden.utility.TokenUtils;

@RestController
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	SocioService socioService;
	
	@PostMapping("/loginuser")
	public ResponseEntity<?> authenticateUser(@RequestBody UserLogin loginRequest){
		
		Authentication authentication;
		
		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		} catch (Exception e) {
			throw new GlobalException(e.getMessage());
		}
		
		Socio user = (Socio)authentication.getPrincipal();
		String jwt = TokenUtils.generateToken(loginRequest.getUsername(), user.getNombre()+" "+user.getApellido(),
				(user.getBarcos().size()>5? "admin":"user"), user.getId_socio());
		
		TokenDTO token = new TokenDTO(jwt);
		
		return ResponseEntity.ok(token);
				
	}

}
