package com.nava.vendas.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nava.vendas.domain.entity.Cliente;

@Repository // esta anotado como uma classe de repositorio a qual vai acessar a base de dados apos uma requisição da classe service
public interface ClientesRepository extends JpaRepository<Cliente, Integer>{//herdando o JpaRepository já estamos implementando diversos metodos default do jpaRepository tais como (salvar, deletar, atualizar, excluir, etc ...)

	//select c from Cliente where c.nome like :nome
	List<Cliente> findByNomeLike(String string);//tem que ter o mesmo nome da variavel que deseja fazer o like
	
	boolean existsByNome(String nome);//estou pedindo para verificar se existe pelo nome 

}
