package com.produtos.controlles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.produtos.models.Perfils;
import com.produtos.repository.PerfilsRepository;


@Controller
public class PerfilsController {
	
	@Autowired
	PerfilsRepository perfilsRepository;
	
	@GetMapping("/perfil{id}")
	public ModelAndView perfil() {
		ModelAndView mv = new ModelAndView("usuarios/perfil");
		return mv;
	}
	
	
	@RequestMapping("/cliente")
	   public ModelAndView index(){
	   return new ModelAndView("usuarios/perfilsForm");
	}
	
	@RequestMapping("/listaClientes")
	@GetMapping
	public ModelAndView listaClientes() {
		ModelAndView mv = new ModelAndView("usuarios/listaPerfils");
		Iterable<Perfils> perfils = perfilsRepository.findAll();
		mv.addObject("clientes", perfils);
		return mv;
	}
	
	
	@PostMapping(value="/cliente")
	public String salvar(Perfils perfils) {
		perfilsRepository.save(perfils);
		return "redirect:cliente";
	}

}
