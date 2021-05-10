package com.nava.vendas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nava.vendas.model.Cliente;
import com.nava.vendas.repositoy.ClientesRepository;

@Service
public class ClientesService {
	
	private ClientesRepository repository;

	@Autowired
	public ClientesService (ClientesRepository repository) {
		this.repository = repository;;
	}
	
	public void salvarCliente(Cliente cliente) {

		ClientesRepository clientesRepository = new ClientesRepository();
		clientesRepository.persistir(cliente);

	}

	public void validarCliente(Cliente cliente) {

	}

}
