package com.joaoldantasn.seguranca.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rh")
@RequiredArgsConstructor
public class RhController {

	@GetMapping("/tecnico")
	@PreAuthorize("hasAnyRole('TECNICO_RH', 'GERENTE_RH', 'ADMIN')")
	public ResponseEntity<String> tecnico(){
		return ResponseEntity.ok("Rota do técnico");
	}
	
	@GetMapping("/gerente")
	@PreAuthorize("hasAnyRole('GERENTE_RH', 'ADMIN')")
	public ResponseEntity<String> gerente(){
		return ResponseEntity.ok("Rota do gerente");
	}
	
}
