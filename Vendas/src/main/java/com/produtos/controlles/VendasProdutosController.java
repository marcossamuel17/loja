package com.produtos.controlles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.produtos.models.ItensCompra;
import com.produtos.models.Produtos;
import com.produtos.models.VendasProdutos;
import com.produtos.repository.ProdutosRepository;
import com.produtos.repository.VendasProdutosRepository;

@Controller
public class VendasProdutosController {

	@Autowired
	ProdutosRepository produtoRepository;

	@Autowired
	VendasProdutosRepository vendasProdutosRepository;

	private List<ItensCompra> itensCompra = new ArrayList<ItensCompra>();

	@GetMapping
	public String exibir() {
		return "estoque/carrinho";
	}

	@GetMapping("/carrinho")
	public ModelAndView listaProdutos() {
		ModelAndView mv = new ModelAndView("estoque/carrinho");
		mv.addObject("listaItens", itensCompra);
		/*
		 * Iterable<Produtos> produto = produtoRepository.findAll();
		 * mv.addObject("produtos", produto);
		 */
		return mv;
	}

	@RequestMapping(value = "/{prdutoID}", method = RequestMethod.POST)
	public String detalhesEventoPost(@PathVariable("prdutoID") long prdutoID, @Valid VendasProdutos vendasProdutos,
			BindingResult result, RedirectAttributes attributes) {

		Produtos produto = produtoRepository.findById(prdutoID);
		vendasProdutos.setProduto(produto);
		vendasProdutosRepository.save(vendasProdutos);

		return "redirect:/{prdutoID}";
	}

	/* Adicionar produtos no carrinho */

	@RequestMapping(value = "/adicionar/{id}", method = RequestMethod.GET)
	public ModelAndView adicionarCarrinho(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("estoque/carrinho");

		Optional<Produtos> prod = Optional.ofNullable(produtoRepository.findById(id));

		Produtos p = prod.get();

		int controle = 0;
		for (ItensCompra it : itensCompra) {
			if (it.getProdutos().getPrdutoID() == (p.getPrdutoID())) {
				it.setQuantidade(it.getQuantidade() + 1);
				controle = 1;
				break;
			}
		}

		if (controle == 0) {
			ItensCompra ic = new ItensCompra();
			ic.setProdutos(p);
			ic.setValorUnitario(p.getPreco());
			ic.setQuantidade(ic.getQuantidade() + 1);
			ic.setValorTotal(ic.getQuantidade() * ic.getValorUnitario());
			itensCompra.add(ic);
		}

		mv.addObject("listaItens", itensCompra);

		return mv;
	}

}
