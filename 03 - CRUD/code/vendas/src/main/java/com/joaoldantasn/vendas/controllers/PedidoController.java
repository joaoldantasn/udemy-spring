package com.joaoldantasn.vendas.controllers;

import static org.springframework.http.HttpStatus.CREATED;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.joaoldantasn.vendas.dtos.InformacaoItemPedidoDTO;
import com.joaoldantasn.vendas.dtos.InformacoesPedidoDTO;
import com.joaoldantasn.vendas.dtos.PedidoDTO;
import com.joaoldantasn.vendas.entities.ItemPedido;
import com.joaoldantasn.vendas.entities.Pedido;
import com.joaoldantasn.vendas.services.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	private PedidoService service;
	
	public PedidoController(PedidoService service) {
		this.service = service;
	}
	
	@PostMapping
	@ResponseStatus(CREATED)
	public Integer save(@RequestBody PedidoDTO dto) {
		Pedido pedido = service.salvar(dto);
		return pedido.getId();
	}
	
	@GetMapping("{id}")
	public InformacoesPedidoDTO getById(@PathVariable Integer id) {
		return service
				.obterPedidoCompleto(id)
				.map(p -> converter(p))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
	}
	
	//metodo converte pedido em dto
	private InformacoesPedidoDTO converter(Pedido pedido) {
		return InformacoesPedidoDTO
					.builder()
					.codigo(pedido.getId())
					.dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
					.cpf(pedido.getCliente().getCpf())
					.nomeCliente(pedido.getCliente().getNome())
					.total(pedido.getTotal())
					.items(converter2(pedido.getItens()))			
					.build();
	}
	
	private List<InformacaoItemPedidoDTO> converter2(Set<ItemPedido> itens){
		if(CollectionUtils.isEmpty(itens)) {
			return Collections.emptyList();
		}
		return itens.stream().map(
				item -> InformacaoItemPedidoDTO
							.builder().descricaoProduto(item.getProduto().getDescricao())
							.precoUnitario(item.getProduto().getPreco())
							.quantidade(item.getQuantidade())
							.build()
				).collect(Collectors.toList());		
	}
	
}
