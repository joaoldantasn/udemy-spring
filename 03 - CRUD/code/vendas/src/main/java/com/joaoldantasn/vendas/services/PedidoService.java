package com.joaoldantasn.vendas.services;

import com.joaoldantasn.vendas.dtos.PedidoDTO;
import com.joaoldantasn.vendas.entities.Pedido;

public interface PedidoService {
	Pedido salvar(PedidoDTO dto);
}
