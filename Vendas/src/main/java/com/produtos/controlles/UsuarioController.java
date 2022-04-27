package com.produtos.controlles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.produtos.models.Usuarios;
import com.produtos.repository.UsuariosRepository;

@Controller
public class UsuarioController {
	
	@Autowired
	UsuariosRepository usuariosRepository;
	
	@GetMapping("/usuario")
	public String exibir() {
		return "usuarios/usuario";
	}
	
	@PostMapping(value="/usuario")
	public String save(Usuarios usuarios) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hasSenha = passwordEncoder.encode(usuarios.getPassword());
		usuarios.setPassword(hasSenha);  
		usuariosRepository.save(usuarios);
		return "redirect:login";
	}

}
