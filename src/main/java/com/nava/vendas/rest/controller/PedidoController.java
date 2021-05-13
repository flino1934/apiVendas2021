package com.nava.vendas.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nava.vendas.domain.entity.Pedido;
import com.nava.vendas.rest.dto.PedidoDto;
import com.nava.vendas.rest.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	private PedidoService pedidoService;

	@Autowired
	public PedidoController(PedidoService service) {
		// TODO Auto-generated constructor stub
		this.pedidoService = service;

	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Integer save(@RequestBody PedidoDto dto) {//vai retornar apenas o id do codigo gerado
		
		Pedido pedido = pedidoService.salvar(dto);
		return pedido.getId();// vai retornar o id do pedido, toda a lógica do pedido está dentro do service
		
	}

}
