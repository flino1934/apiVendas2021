package com.nava.vendas.rest.controller;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.nava.vendas.domain.entity.ItemPedido;
import com.nava.vendas.domain.entity.Pedido;
import com.nava.vendas.domain.enums.StatusPedido;
import com.nava.vendas.rest.dto.AtualizacaoStatusDto;
import com.nava.vendas.rest.dto.InformacaoItemPedidoDto;
import com.nava.vendas.rest.dto.InformacoesPedidoDto;
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
	public Integer save(@RequestBody @Valid PedidoDto dto) {//vai retornar apenas o id do codigo gerado
		
		Pedido pedido = pedidoService.salvar(dto);
		return pedido.getId();// vai retornar o id do pedido, toda a lógica do pedido está dentro do service
		
	}
	
	@GetMapping("/{id}")
	public InformacoesPedidoDto getById(@PathVariable Integer id) {//esse metodo vai pesquisar o pedido pelo id e trazer para a tela e será o mettodo principal porem ele irá depender de outros métodos para executar ele
		//ele será o primeiro e ultimo a ser execultado na serie dele 
		
		return pedidoService
				.obterPedidoCompleto(id)//esse metodo vem de pedido service
				.map( p -> converter(p))//esse metodo vem de baixo daqui kkkkkk que são metdos auxiliares 
				.orElseThrow(() -> 
				 new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Pedido não encontrado"));
		
	}

	private InformacoesPedidoDto converter(Pedido pedido) {//vai pegar os dados de InformacoesPedidoDto para mandar para o metodo InformacoesPedidoDto getById()
		
		return InformacoesPedidoDto
			.builder()
			.codigo(pedido.getId())
			.dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
			.cpf(pedido.getCliente().getCpf())
			.nomeCliente(pedido.getCliente().getNome())
			.totalPedido(pedido.getTotal())
			.status(pedido.getStatus().name())//utiliza este metodo name pq ele pe o enum e converte para String
			.items(converter(pedido.getItens()))//vai depender do metodo de baixo pois em  InformacoesPedidoDto tem uma lista de items que será populada com os dados InformacaoItemPedidoDto
			.build();
		
	}
	
    private List<InformacaoItemPedidoDto> converter(List<ItemPedido> itens){// vai pegar os dados de InformacaoItemPedidoDto para mandar para o metodo InformacoesPedidoDto getById()
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }
        return itens.stream().map(
                item -> InformacaoItemPedidoDto
                            .builder().descricaoProduto(item.getProduto().getDescricao())
                            .precoUnitario(item.getProduto().getPreco())
                            .quantidade(item.getQuantidade())
                            .build()
        ).collect(Collectors.toList());
    }
	
    @PatchMapping("/{id}")//utilizamos esse verbo para atualizar apenas uma parte ja o put ele vai querer todos os parametros oq daria muito trabalho
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus( @PathVariable("id") Integer id,@RequestBody @Valid AtualizacaoStatusDto dto) {// esta chamando da classe PedidoService que é implementada pela classe PedidoServiceImpl
    	
    	String novoStatus = dto.getNovoStatus();// vai armazenar o novo status que vem da classe AtualizacaoStatusDto esta entrando na classe e pegando pelo metodo get 
    	
    	pedidoService.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    	
    }
    
    
	
}
