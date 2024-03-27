package com.joaoldantasn.vendas.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joaoldantasn.vendas.entities.Cliente;
import com.joaoldantasn.vendas.repositories.ClienteRepository;

@Controller
@RequestMapping("/api")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clientes;

	@RequestMapping(value ="/hello/{nome}", method = RequestMethod.GET)
	@ResponseBody
	public String helloCliente(@PathVariable("nome") String nomeCliente) {
		return String.format("Hello %s", nomeCliente);
	}
	
	@GetMapping("/clientes/{id}")
	@ResponseBody
	public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Integer id) {
		Optional<Cliente> cliente =  clientes.findById(id);
		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/clientes")
	@ResponseBody
	public ResponseEntity save(@RequestBody Cliente cliente) {
		Cliente clienteSalvo = clientes.save(cliente);
		return ResponseEntity.ok(clienteSalvo);
	}
	
	@DeleteMapping("/clientes/{id}")
	@ResponseBody
	public ResponseEntity delete(@PathVariable Integer id) {
		Optional<Cliente> cliente =  clientes.findById(id);
		if(cliente.isPresent()) {
			clientes.delete(cliente.get());
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	
}
