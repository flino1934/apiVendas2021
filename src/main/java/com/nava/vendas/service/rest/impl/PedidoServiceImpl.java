package com.nava.vendas.service.rest.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nava.vendas.domain.entity.Cliente;
import com.nava.vendas.domain.entity.ItemPedido;
import com.nava.vendas.domain.entity.Pedido;
import com.nava.vendas.domain.entity.Produto;
import com.nava.vendas.domain.repository.ClientesRepository;
import com.nava.vendas.domain.repository.ItemsPedidosRepository;
import com.nava.vendas.domain.repository.PedidoRepository;
import com.nava.vendas.domain.repository.ProdutosRepository;
import com.nava.vendas.exception.RegraNegocioExcption;
import com.nava.vendas.rest.dto.ItemPedidoDto;
import com.nava.vendas.rest.dto.PedidoDto;
import com.nava.vendas.rest.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService{
	
	private final PedidoRepository pedidoRepository;
	private final ClientesRepository clientesRepository;//como eu preciso do cliente então instaciamos uma dependencia de cliente Repository pois ele terá acesso a BD a qual trara o cliente pelo id 
	private final ProdutosRepository produtosRepository;
	private final ItemsPedidosRepository  itemsPedidosRepository;
	
	public PedidoServiceImpl(
								PedidoRepository pedidoRepository,
								ClientesRepository clientesRepository,
								ProdutosRepository produtosRepository,
								ItemsPedidosRepository  itemsPedidosRepository) {
		
		this.pedidoRepository = pedidoRepository;
		this.clientesRepository = clientesRepository;
		this.produtosRepository = produtosRepository;
		this.itemsPedidosRepository = itemsPedidosRepository;
	}

	@Override
	@Transactional//vai garantir a integridade do pedido caso ocorra algum erro acontecera um rollback
	public Pedido salvar(PedidoDto dto) {
		Integer idCliente = dto.getCliente();//vai pegar o id do cliente 
		Cliente cliente = 
				clientesRepository.findById(idCliente).orElseThrow( () -> new RegraNegocioExcption("Código de cliente invalido"));
		
		//vai ser Criado o pedido e vai popular ele só não tera o ID
		Pedido pedido = new Pedido();//criei a instancia de pedido e teremos que popular com os valores 
		pedido.setTotal(dto.getTotal());//como o dto que vai ter os dados então será passado pelo dto o total
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(cliente);
		
		List<ItemPedido> itemsPedidos = converterItems(pedido, dto.getItems());//quando chegar aqui ele vai descer para o metodo de converter pedido antes de salvar o pedido
		
		pedidoRepository.save(pedido);//depois de retornar do metodo converterItems() ele vai vir e salvar o pedido ai sim tera um id
		itemsPedidosRepository.saveAll(itemsPedidos);//depois ja ter populadoo items pedido no metodo converterItems() ira salvar o pedido
		pedido.setItens(itemsPedidos);//esta passando os itens do pedido para a lista de itens de pedidos da classe pedido
		
		return pedido;
	}
	
	private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDto> items) {
		
		if (items.isEmpty()) {//vai verificar se a lista está vazia caso esteja vazia vai retornar erro
			
			throw new RegraNegocioExcption("Não é possivel realizar pedido sem items");
			
		}
		
		return items
				.stream()
				.map(dto ->{
					Integer idProduto = dto.getProduto();
					Produto produto = produtosRepository
						.findById(idProduto)
						.orElseThrow(() -> new RegraNegocioExcption("Codigo de produto inválido: "+idProduto));
					
					ItemPedido itemPedido = new ItemPedido();//vai popular o o item do pedido poi o pedido tem itens 
					itemPedido.setQuantidade(dto.getQuantidade());
					itemPedido.setPedido(pedido);
					itemPedido.setProduto(produto);//vai passar o produto
					return itemPedido;
				}).collect(Collectors.toList());
		
	}

}
