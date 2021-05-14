package com.nava.vendas.rest.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.nava.vendas.domain.repository.ClientesRepository;

@RestController
@RequestMapping("/api/clientes") // esta falando a url desta api
public class ClienteController {

	private ClientesRepository clienteRepository;

	@Autowired
	public ClienteController(ClientesRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@GetMapping("/{id}")
	public Cliente getClienteById( @PathVariable("id") Integer id) {

		return clienteRepository
					.findById(id)
					.orElseThrow(() -> 
					 new ResponseStatusException(HttpStatus.NOT_FOUND,
                             "Cliente não encontrado"));

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente saveCliente(@RequestBody @Valid Cliente cliente){//esta anotado com requestBody pois ele vai receber parametros pelo corpo da requisição

		return clienteRepository.save(cliente);// acessou o repositorio e persistiu na BD

	}
	
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id ){
    	
        clienteRepository.findById(id)
                .map( cliente -> {
                    clienteRepository.delete(cliente );
                    return cliente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado") );

    }
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateCliente( @Valid @RequestBody Cliente cliente, 
												@PathVariable("id") Integer id){
		
		clienteRepository
				.findById(id).map(clienteExistente ->{
					cliente.setId(clienteExistente.getId());
					clienteRepository.save(cliente);
					return clienteExistente;
				}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado") );
		
	}
	
	@GetMapping
	public List<Cliente> find( Cliente filtro) {
		
		ExampleMatcher matcher = ExampleMatcher
									.matching()
									.withIgnoreCase()
									.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);//qualquer lugar que ele encontrar a String ele vai retornar talvez funcione para alto complete
		Example example = Example.of(filtro,matcher);
		return clienteRepository.findAll(example);
		
	}
	
 

}



