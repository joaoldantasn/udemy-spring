package com.joaoldantasn.seguranca.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joaoldantasn.seguranca.entities.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, String> {

	Optional<Grupo> findByNome(String nome);
	
}
