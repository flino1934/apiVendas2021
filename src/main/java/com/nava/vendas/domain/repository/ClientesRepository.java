package com.nava.vendas.domain.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nava.vendas.domain.entity.Cliente;

@Repository//esta anotado como uma classe de repositorio a qual vai acessar a base de dados apos uma requisição da classe service
public class ClientesRepository {

	@Autowired
	private EntityManager entityManager;

	@Transactional//esta falando que esse metodo vai ser transacionado pelo jpa
	public Cliente salvar(Cliente cliente) {

		entityManager.persist(cliente);
		return cliente;

	}
	
}
