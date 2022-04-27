package com.produtos.controlles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.produtos.models.Produtos;
import com.produtos.models.VendasProdutos;
import com.produtos.repository.ProdutosRepository;
import com.produtos.repository.VendasProdutosRepository;

@Controller
public class ProdutoController {
	
	private static String caminhoImagem = "C:/Users/marco/eclipse-workspace/Projetos/imagensProdutos/";
	
	@Autowired
	ProdutosRepository produtoRepository;
	
	@Autowired
	VendasProdutosRepository vendasProdutosRepository;
	
	@RequestMapping("/produtos")
	public String form(Produtos produto){
		return "produto/cadastrarProduto";
	}
	
	@RequestMapping("/listaProdutos")
	@GetMapping
	public ModelAndView listaProdutos() {
		ModelAndView mv = new ModelAndView("produto/listaProdutos");
		Iterable<Produtos> produto = produtoRepository.findAll();
		mv.addObject("produtos", produto);
		return mv;
	}
	
	@PostMapping("/produtos")
	public ModelAndView salvarProduto(Produtos produto, BindingResult result ,@RequestParam("imagem") MultipartFile file ) throws IOException {
		ModelAndView mv = new ModelAndView("produto/listaProdutos");
		
		if(result.hasErrors()) {
			return mv;
		}
		
		produtoRepository.save(produto);
		
		try {
			
			if(!file.isEmpty()) {
				byte[] bytes = file.getBytes();
				Path caminho = Paths.get(caminhoImagem+String.valueOf(produto.getPrdutoID()) +file.getOriginalFilename());
				Files.write(caminho, bytes);
				produto.setImagemProduto(String.valueOf(produto.getPrdutoID()) +file.getOriginalFilename());
				produtoRepository.save(produto);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return mv;
	}
	
	// Metodo para exibir imagem pegando o nome salvo no banvo de dados e o caminho especificado na variavel caminhoImagem.
	
	@GetMapping(value="/exibir/{imagem}")
	@ResponseBody
	public byte[] exibirImagem(@PathVariable("imagem") String imagem) throws IOException{
		File imagemArquivo = new File(caminhoImagem+imagem);
		
		return Files.readAllBytes(imagemArquivo.toPath());
	}
	
	
	
	
	
	@RequestMapping(value="/{prdutoID}", method=RequestMethod.GET)
	public ModelAndView detalhesEvento(@PathVariable("prdutoID") long prdutoID){
		Produtos produto = produtoRepository.findById(prdutoID);
		ModelAndView mv = new ModelAndView("estoque/vendasForm");
		mv.addObject("produto", produto);
		
		
		return mv;
	}
	
	@RequestMapping("/deletarProdutos/{prdutoID}")
	@DeleteMapping
	public String deletarProduto(@PathVariable long prdutoID){
		Produtos produto = produtoRepository.findById(prdutoID);
		produtoRepository.delete(produto);
		return "redirect:/listaProdutos";
	}
	

}
