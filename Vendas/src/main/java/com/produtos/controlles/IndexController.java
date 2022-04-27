package com.produtos.controlles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.produtos.models.Produtos;
import com.produtos.models.Usuarios;
import com.produtos.repository.ProdutosRepository;
import com.produtos.repository.UsuariosRepository;

@Controller
public class IndexController {
	
	@Autowired
	ProdutosRepository produtoRepository;
	
	@Autowired
	UsuariosRepository usuariosRepository;
	
	@RequestMapping("/")
	@GetMapping
	public ModelAndView listaProdutos() {
		ModelAndView mv = new ModelAndView("/index");
		Iterable<Produtos> produto = produtoRepository.findAll();
		mv.addObject("produtos", produto);
		return mv;
	}
	
	@RequestMapping("/login")
	@GetMapping
	public String login() {
		return "login";
	}
	
	@PostMapping(value="/")
	public String form(Usuarios usuarios) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hasSenha = passwordEncoder.encode(usuarios.getPassword());
		usuarios.setPassword(hasSenha);  
		usuariosRepository.save(usuarios);
		return "redirect:listaProdutos";
	}
	
	@RequestMapping("/login-error")
	public String loginError(ModelMap modelMap){
		modelMap.addAttribute("alerta", "Erro");
		modelMap.addAttribute("titulo", "invalido");
		modelMap.addAttribute("texto", "Login ou senha invalidos!");
		modelMap.addAttribute("subtexto", "Acesso não permitido!");
		
		return "login";
	}

}
