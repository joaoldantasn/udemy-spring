package com.joaoldantasn.vendas.services;

import java.util.Optional;

import com.joaoldantasn.vendas.dtos.PedidoDTO;
import com.joaoldantasn.vendas.entities.Pedido;

public interface PedidoService {
	Pedido salvar(PedidoDTO dto);
	
	Optional<Pedido> obterPedidoCompleto(Integer id);
}
