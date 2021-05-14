package com.nava.vendas.rest.dto;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public class InformacaoItemPedidoDto {

	private String descricaoProduto;
	private BigDecimal precoUnitario;
	private Integer quantidade;
	
	public InformacaoItemPedidoDto() {
		// TODO Auto-generated constructor stub
	}

	public InformacaoItemPedidoDto(String descricaoProduto, BigDecimal precoUnitario, Integer quantidade) {
		super();
		this.descricaoProduto = descricaoProduto;
		this.precoUnitario = precoUnitario;
		this.quantidade = quantidade;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "InformacaoItemPedidoDto [descricaoProduto=" + descricaoProduto + ", precoUnitario=" + precoUnitario
				+ ", quantidade=" + quantidade + "]";
	}
	
	
	
	
}
