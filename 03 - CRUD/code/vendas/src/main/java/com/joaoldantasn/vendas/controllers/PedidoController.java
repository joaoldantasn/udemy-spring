package com.joaoldantasn.vendas.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaoldantasn.vendas.services.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController implements PedidoService{

	private PedidoService service;
	
	public PedidoController(PedidoService service) {
		this.service = service;
	}
	
}
