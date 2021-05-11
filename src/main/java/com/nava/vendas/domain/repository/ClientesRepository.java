package com.nava.vendas.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nava.vendas.domain.entity.Cliente;

@Repository // esta anotado como uma classe de repositorio a qual vai acessar a base de dados apos uma requisição da classe service
public interface ClientesRepository extends JpaRepository<Cliente, Integer>{//herdando o JpaRepository já estamos implementando diversos metodos default do jpaRepository tais como (salvar, deletar, atualizar, excluir, etc ...)

	List<Cliente> findByNomeLike(String string);

}
