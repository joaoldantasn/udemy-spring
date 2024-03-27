package com.joaoldantasn.vendas;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.joaoldantasn.vendas.entities.Cliente;
import com.joaoldantasn.vendas.entities.Pedido;
import com.joaoldantasn.vendas.repositories.ClienteRepository;
import com.joaoldantasn.vendas.repositories.PedidoRepository;

@SpringBootApplication
public class VendasApplication {
	
	@Bean
	public CommandLineRunner init(@Autowired ClienteRepository repository, @Autowired PedidoRepository pedidos) {
		return args -> {
			Cliente cliente = new Cliente();
			cliente.setNome("juliette");
			repository.save(cliente);
			
			Pedido p = new Pedido();
			p.setCliente(cliente);
			p.setDataPedido(LocalDate.now());
			p.setTotal(BigDecimal.valueOf(100));
			pedidos.save(p);
			
			Cliente fulano = repository.findClienteFetchPedidos(cliente.getId());
			System.out.println(fulano);
			System.out.println(fulano.getPedidos());
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
