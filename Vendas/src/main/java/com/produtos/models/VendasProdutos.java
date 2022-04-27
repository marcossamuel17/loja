package com.produtos.models;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class VendasProdutos {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long vandasId;
	
	private String quantidadeVendas;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCompra = new Date();
	private String formaDePagamento;
	private Double valorTotal;
	
	@ManyToOne
	private Usuarios usuarios;
	
	@ManyToOne
	@JoinColumn(name="produtos_id")
	private Produtos produtos;
	
	

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public String getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(String formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Produtos getProdutos() {
		return produtos;
	}

	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public Produtos getProduto() {
		return produtos;
	}

	public void setProduto(Produtos produtos) {
		this.produtos = produtos;
	}

	public long getVandasId() {
		return vandasId;
	}

	public void setVandasId(long vandasId) {
		this.vandasId = vandasId;
	}

	public String getQuantidadeVendas() {
		return quantidadeVendas;
	}

	public void setQuantidadeVendas(String quantidadeVendas) {
		this.quantidadeVendas = quantidadeVendas;
	}
	
	

}
