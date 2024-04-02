package com.joaoldantasn.vendas.exceptions;

@SuppressWarnings("serial")
public class PedidoNaoEncontradoException extends RuntimeException{
	
	public PedidoNaoEncontradoException() {
		super("Pedido não encontrado");
	}
	
}
