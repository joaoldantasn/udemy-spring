package com.joaoldantasn.vendas.services.impl;

import org.springframework.stereotype.Service;

import com.joaoldantasn.vendas.repositories.PedidoRepository;

@Service
public class PedidoServiceImpl {

	
	private PedidoRepository repository;
	
	public PedidoServiceImpl(PedidoRepository repository) {
		this.repository = repository;
	}
	
}
