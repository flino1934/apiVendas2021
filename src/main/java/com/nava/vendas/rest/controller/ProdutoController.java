package com.nava.vendas.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.nava.vendas.domain.entity.Cliente;
import com.nava.vendas.domain.entity.Produto;
import com.nava.vendas.domain.repository.ProdutosRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	private ProdutosRepository produtoRepository;

	@Autowired
	public ProdutoController(ProdutosRepository repository) {

		this.produtoRepository = repository;

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto saveProduto(@RequestBody Produto produto) {

		return produtoRepository.save(produto);//vai acessar a BD e salvar o produto

	}
	
	@GetMapping("/{id}")
	public Produto getProdutoId( @PathVariable Integer id) {
		
		return produtoRepository.findById(id)//vai pesquisar o o produto por id na BD
								.orElseThrow(() ->
								new ResponseStatusException(HttpStatus.NOT_FOUND,
			                             "Produto não encontrado"));//caso não encontre vai retornar esse erro
			
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduto( @PathVariable Integer id) {
		
		produtoRepository.findById(id)
		.map(produto ->{
			produtoRepository.delete(produto);
			return produto;
		}).orElseThrow(() ->
		new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Produto não encontrado"));
		
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateProduto( @RequestBody Produto produto, @PathVariable("id") Integer id) {
		
		produtoRepository.findById(id).map( produtoExistente->{
			produto.setId(produtoExistente.getId());
			produtoRepository.save(produto);
			return produtoExistente;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Produto não encontrado! ") );							
		
	}
	
	@GetMapping
	public List<Cliente> find( Produto filtro) {
		
		ExampleMatcher matcher = ExampleMatcher
									.matching()
									.withIgnoreCase()
									.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);//qualquer lugar que ele encontrar a String ele vai retornar talvez funcione para alto complete
		Example example = Example.of(filtro,matcher);
		return produtoRepository.findAll(example);
		
	}
	


}
