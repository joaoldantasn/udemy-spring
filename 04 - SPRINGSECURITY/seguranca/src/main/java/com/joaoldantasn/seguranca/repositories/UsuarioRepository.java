package com.joaoldantasn.seguranca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joaoldantasn.seguranca.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

}
