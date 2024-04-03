package com.joaoldantasn.vendas.dtos;

import java.math.BigDecimal;
import java.util.List;

import com.joaoldantasn.vendas.validation.NotEmptyList;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
	
	@NotNull(message = "{campo.codigo-cliente.obrigatorio}")
	private Integer cliente;
	@NotNull(message = "{campo.total-pedido.obrigatorio}")
	private BigDecimal total;
	@NotEmptyList(message = "{campo.items-pedido.obrigatorio}")
	private List<ItemPedidoDTO> items;
	
}
