package com.joaoldantasn.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joaoldantasn.vendas.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {	
	
}