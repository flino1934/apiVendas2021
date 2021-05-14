package com.nava.vendas.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;

@Builder
public class InformacoesPedidoDto {// vai trazer as informações do pedido

	private Integer codigo;
	private String cpf;
	private String nomeCliente;
	private BigDecimal totalPedido;
	private String dataPedido;
	private List<InformacaoItemPedidoDto> items;// sera criada uma lista com a informação do itens do pedido e será populada para trazer informações

	public InformacoesPedidoDto() {
		// TODO Auto-generated constructor stub
	}

	public InformacoesPedidoDto(Integer codigo, String cpf, String nomeCliente, BigDecimal totalPedido,
			String dataPedido, List<InformacaoItemPedidoDto> items) {

		this.codigo = codigo;
		this.cpf = cpf;
		this.nomeCliente = nomeCliente;
		this.totalPedido = totalPedido;
		this.dataPedido = dataPedido;
		this.items = items;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public BigDecimal getTotalPedido() {
		return totalPedido;
	}

	public void setTotalPedido(BigDecimal totalPedido) {
		this.totalPedido = totalPedido;
	}

	public List<InformacaoItemPedidoDto> getItems() {
		return items;
	}

	public void setItems(List<InformacaoItemPedidoDto> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "InformacoesPedidoDto [codigo=" + codigo + ", cpf=" + cpf + ", nomeCliente=" + nomeCliente
				+ ", totalPedido=" + totalPedido + ", items=" + items + "]";
	}

	public String getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(String dataPedido) {
		this.dataPedido = dataPedido;
	}

}
