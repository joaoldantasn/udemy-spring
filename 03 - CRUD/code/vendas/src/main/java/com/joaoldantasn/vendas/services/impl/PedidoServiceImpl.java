package com.joaoldantasn.vendas.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoldantasn.vendas.dtos.ItemPedidoDTO;
import com.joaoldantasn.vendas.dtos.PedidoDTO;
import com.joaoldantasn.vendas.entities.Cliente;
import com.joaoldantasn.vendas.entities.ItemPedido;
import com.joaoldantasn.vendas.entities.Pedido;
import com.joaoldantasn.vendas.entities.Produto;
import com.joaoldantasn.vendas.exceptions.RegraDeNegocioException;
import com.joaoldantasn.vendas.repositories.ClienteRepository;
import com.joaoldantasn.vendas.repositories.ItemPedidoRepository;
import com.joaoldantasn.vendas.repositories.PedidoRepository;
import com.joaoldantasn.vendas.repositories.ProdutoRepository;
import com.joaoldantasn.vendas.services.PedidoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService{

	
	private final PedidoRepository repository;
	private final ClienteRepository clientesRepository;
	private final ProdutoRepository produtoRepository;
	private final ItemPedidoRepository itensPedidoRepository;
	

	@Override
	@Transactional
	public Pedido salvar(PedidoDTO dto) {
		Integer idCliente = dto.getCliente();
		Cliente cliente = clientesRepository.findById(idCliente)
								.orElseThrow(() -> new RegraDeNegocioException("Código de cliente inválido"));
		Pedido pedido = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(cliente);
		
		List<ItemPedido> itensPedidos = converterItens(pedido, dto.getItems());
		repository.save(pedido);
		itensPedidoRepository.saveAll(itensPedidos);
		return pedido;
	}
	
	private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> items) {
		if(items.isEmpty()) {
			throw new RegraDeNegocioException("Não é possivel realizar um pedido sem itens.");
		}
		return items
				.stream()
				.map(dto -> {
					Integer idProduto = dto.getProduto();
					Produto produto = produtoRepository.findById(idProduto)
									.orElseThrow(() -> new RegraDeNegocioException("Código de cliente inválido: " + idProduto));
					
					ItemPedido itemPedido = new ItemPedido();
					itemPedido.setQuantidade(dto.getProduto());
					itemPedido.setPedido(pedido);
					itemPedido.setProduto(produto);
					return itemPedido;
				}).collect(Collectors.toList());
	}

	@Override
	public Optional<Pedido> obterPedidoCompleto(Integer id) {
		return repository.findByIdFetchItens(id);
	}
	
}
