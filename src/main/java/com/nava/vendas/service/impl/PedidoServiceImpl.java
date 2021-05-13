package com.nava.vendas.service.impl;

import org.springframework.stereotype.Service;

import com.nava.vendas.domain.repository.PedidoRepository;
import com.nava.vendas.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService{
	
	private PedidoRepository pedidoRepository;

	public PedidoServiceImpl(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}
	
	

}
