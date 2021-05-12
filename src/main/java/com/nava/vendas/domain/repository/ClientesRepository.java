package com.nava.vendas.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nava.vendas.domain.entity.Cliente;

@Repository // esta anotado como uma classe de repositorio a qual vai acessar a base de dados apos uma requisição da classe service
public interface ClientesRepository extends JpaRepository<Cliente, Integer>{//herdando o JpaRepository já estamos implementando diversos metodos default do jpaRepository tais como (salvar, deletar, atualizar, excluir, etc ...)

	//select c from Cliente where c.nome like :nome
	List<Cliente> findByNomeLike(String string);//tem que ter o mesmo nome da variavel que deseja fazer o like estou usando o QueryMethods
	
	@Query(value = " select * from tb_cliente c where c.nome like '%:nome%' ",nativeQuery = true)//não estamos usando QueryMethods estamos utilizando apenas @Query
	List<Cliente> EncontrarPorNome( @Param("nome") String string);//tem que ter o mesmo nome da variavel que deseja fazer o like

	@Query(" delete from Cliente c where c.nome =:nome ")
	@Modifying//esta dizendo que estou mechendo na tabela de registro
	void deleteByNome(String nome);
	
	boolean existsByNome(String nome);//estou pedindo para verificar se existe pelo nome estou usando o QueryMethods
	
	@Query(" select c from Cliente c left join fetch c.pedido where c.id =:id ")//estamos usando jpql
	Cliente findClienteFetchPedidos( @Param("id")Integer id);
	
	

}
