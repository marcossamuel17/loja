package com.produtos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.produtos.repository.UsuariosRepository;

@Service
public class UsuariosUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UsuariosRepository usuariosRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return Optional.ofNullable(usuariosRepository.findByUsername(username))
				.orElseThrow(()-> new UsernameNotFoundException("Usuario não encontrado"));
	}

}
