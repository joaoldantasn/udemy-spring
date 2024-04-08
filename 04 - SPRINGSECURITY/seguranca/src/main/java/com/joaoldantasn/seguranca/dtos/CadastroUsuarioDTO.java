package com.joaoldantasn.seguranca.dtos;

import java.util.List;

import com.joaoldantasn.seguranca.entities.Usuario;

import lombok.Data;

@Data
public class CadastroUsuarioDTO {
	private Usuario usuario;
	private List<String> permissoes;
}
