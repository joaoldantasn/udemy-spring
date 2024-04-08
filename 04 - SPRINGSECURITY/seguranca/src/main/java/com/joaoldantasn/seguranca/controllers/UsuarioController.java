package com.joaoldantasn.seguranca.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaoldantasn.seguranca.dtos.CadastroUsuarioDTO;
import com.joaoldantasn.seguranca.entities.Usuario;
import com.joaoldantasn.seguranca.services.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
	
	private final UsuarioService service;

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Usuario> salvar(@RequestBody CadastroUsuarioDTO body){
		Usuario usuarioSalvo = service.salvar(body.getUsuario(), body.getPermissoes());
		return ResponseEntity.ok(usuarioSalvo);
	}
}
