package com.produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.produtos.models.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos, Long>{
	Produtos findById(long prdutoID);
}
