package com.nava.vendas.rest.service;

import java.util.Optional;

import com.nava.vendas.domain.entity.Pedido;
import com.nava.vendas.domain.enums.StatusPedido;
import com.nava.vendas.rest.dto.PedidoDto;

public interface PedidoService {
	
	Pedido salvar(PedidoDto dto);//vai salvar o pedido toda a regra de negocio esta na classe PedidoServiceImpl

	Optional<Pedido> obterPedidoCompleto(Integer id);//vai trazer o pedido completo e sera implementado pelo PedidoServiceImpl classe a qual esta toda a regra de negocio
	
	void atualizaStatus(Integer id,StatusPedido status);// vai atualizar o status do pedido sera implementado pelo PedidoServiceImpl classe a qual esta toda a regra de negocio
	
}
