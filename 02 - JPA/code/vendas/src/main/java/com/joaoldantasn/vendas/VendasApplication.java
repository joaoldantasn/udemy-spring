package com.joaoldantasn.vendas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.joaoldantasn.vendas.entities.Cliente;
import com.joaoldantasn.vendas.repositories.ClienteRepository;

@SpringBootApplication
public class VendasApplication {
	
	@Bean
	public CommandLineRunner init(@Autowired ClienteRepository repository) {
		return args -> {
			Cliente cliente = new Cliente();
			cliente.setNome("juliette");
			repository.salvar(cliente);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
