package com.nava.vendas.rest.service;

import com.nava.vendas.domain.entity.Pedido;
import com.nava.vendas.rest.dto.PedidoDto;

public interface PedidoService {
	
	Pedido salvar(PedidoDto dto);//vai salvar o pedido

}
