package com.produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.produtos.models.Produtos;
import com.produtos.models.VendasProdutos;

public interface VendasProdutosRepository extends JpaRepository<VendasProdutos, Long>{
	

}
