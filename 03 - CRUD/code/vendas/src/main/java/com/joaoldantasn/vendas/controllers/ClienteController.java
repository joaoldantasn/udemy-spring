package com.joaoldantasn.vendas.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.joaoldantasn.vendas.entities.Cliente;
import com.joaoldantasn.vendas.repositories.ClienteRepository;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clientes;

	@RequestMapping(value ="/hello/{nome}", method = RequestMethod.GET)
	public String helloCliente(@PathVariable("nome") String nomeCliente) {
		return String.format("Hello %s", nomeCliente);
	}
	
	@GetMapping("{id}")
	public Cliente getClienteById(@PathVariable("id") Integer id) {
		Optional<Cliente> cliente =  clientes.findById(id);
		if(cliente.isPresent()) {
			return cliente.get();
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
	}
	
	@PostMapping
	public ResponseEntity save(@RequestBody Cliente cliente) {
		Cliente clienteSalvo = clientes.save(cliente);
		return ResponseEntity.ok(clienteSalvo);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity delete(@PathVariable Integer id) {
		Optional<Cliente> cliente =  clientes.findById(id);
		if(cliente.isPresent()) {
			clientes.delete(cliente.get());
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable Integer id, @RequestBody Cliente cliente) {
		return clientes
				.findById(id)
				.map(clienteExistente -> {
					cliente.setId(clienteExistente.getId());
					clientes.save(cliente);
					return ResponseEntity.noContent().build();
				}).orElseGet( () -> ResponseEntity.notFound().build());
	}
	
	@GetMapping
	public ResponseEntity find (Cliente filtro) {
		ExampleMatcher matcher = ExampleMatcher
									.matching()
									.withIgnoreCase()
									.withStringMatcher(
											StringMatcher.CONTAINING);
		Example example = Example.of(filtro, matcher);
		List<Cliente> lista = clientes.findAll(example);
		return ResponseEntity.ok(lista);
	}
	
}
