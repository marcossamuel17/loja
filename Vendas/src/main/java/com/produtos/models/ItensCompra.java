package com.produtos.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItensCompra {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long itensCompraId;
	
	@ManyToOne
	private Produtos produtos;
	
	@ManyToOne
	private VendasProdutos vendasProdutos;
	
	private Integer quantidade = 0;
	private double valorUnitario;
	private double valorTotal;
	
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public long getItensCompraId() {
		return itensCompraId;
	}
	public void setItensCompraId(long itensCompraId) {
		this.itensCompraId = itensCompraId;
	}
	public Produtos getProdutos() {
		return produtos;
	}
	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}
	public VendasProdutos getVendasProdutos() {
		return vendasProdutos;
	}
	public void setVendasProdutos(VendasProdutos vendasProdutos) {
		this.vendasProdutos = vendasProdutos;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
	

}
