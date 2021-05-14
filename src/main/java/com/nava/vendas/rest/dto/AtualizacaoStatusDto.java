package com.nava.vendas.rest.dto;

public class AtualizacaoStatusDto {

	private String novoStatus;// vai atualizar o status do pedido

	public AtualizacaoStatusDto() {
		// TODO Auto-generated constructor stub
	}

	public AtualizacaoStatusDto(String novoStatus) {

		this.novoStatus = novoStatus;
	}

	public String getNovoStatus() {
		return novoStatus;
	}

	public void setNovoStatus(String novoStatus) {
		this.novoStatus = novoStatus;
	}

	@Override
	public String toString() {
		return "AtualizacaoStatusDto [novoStatus=" + novoStatus + "]";
	}

}
