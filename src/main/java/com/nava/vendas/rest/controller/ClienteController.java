package com.nava.vendas.rest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Cliente> getClienteById( @PathVariable("id") Integer id) {

		Optional<Cliente> cliente = clienteRepository.findById(id);//esta guardadno o cliente que foi buscado no banco pelo id
		
		if (cliente.isPresent()) {//esta verificando se o cliente esta presente no BD
			return ResponseEntity.ok().body(cliente.get());//como o ResponseEntity esta passando um ok se ele encontrar um cliente ele vai devolver o codigo de status 200 
		}else {
			return ResponseEntity.notFound().build();
		}
		

	}

}
