package com.joaoldantasn.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.joaoldantasn.vendas.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	@Query("select c from Cliente c left join fetch c.pedidos where c.id =:id")
	Cliente findClienteFetchPedidos(@Param("id") Integer id);
	
}
