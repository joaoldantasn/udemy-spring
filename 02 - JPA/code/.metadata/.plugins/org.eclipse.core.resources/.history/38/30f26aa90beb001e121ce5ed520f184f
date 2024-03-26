package com.joaoldantasn.vendas.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.joaoldantasn.vendas.entities.Cliente;

import jakarta.persistence.EntityManager;

@Repository
public class ClienteRepository {
		
	
	@Autowired
	private EntityManager entityManager;

	@Transactional
	public Cliente salvar(Cliente cliente) {
		entityManager.persist(cliente);
		return cliente;
	}
	
	
}
