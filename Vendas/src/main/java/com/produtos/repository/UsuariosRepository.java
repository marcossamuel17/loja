package com.produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.produtos.models.Usuarios;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long>{
	
	Usuarios findByUsername(String username);

}
