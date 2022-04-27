package com.produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.produtos.models.Perfils;

public interface ItensCompraRepository extends JpaRepository<Perfils, Long>{
	

}
