package com.produtos.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;


@Entity
public class Produtos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long prdutoID;
	
	private String nomeProduto;
	private double preco;
	private String descricao;
	private String marca;
	private String fornecedor;
	private String quantidade;
	
	private String imagemProduto;
	
	@OneToMany
	private List<VendasProdutos> vendasProdutos;
	
	@OneToMany
	private List<ItensCompra> itensCompra;
	
	public List<ItensCompra> getItensCompra() {
		return itensCompra;
	}
	public void setItensCompra(List<ItensCompra> itensCompra) {
		this.itensCompra = itensCompra;
	}
	public String getImagemProduto() {
		return imagemProduto;
	}
	public void setImagemProduto(String imagemProduto) {
		this.imagemProduto = imagemProduto;
	}
	public List<VendasProdutos> getVendasProdutos() {
		return vendasProdutos;
	}
	public void setVendasProdutos(List<VendasProdutos> vendasProdutos) {
		this.vendasProdutos = vendasProdutos;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	
	public long getPrdutoID() {
		return prdutoID;
	}
	public void setPrdutoID(long prdutoID) {
		this.prdutoID = prdutoID;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public Produtos get() {
		// TODO Auto-generated method stubS
		return null;
	}
	
	
}
