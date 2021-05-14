package com.nava.vendas.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nava.vendas.domain.entity.Cliente;
import com.nava.vendas.domain.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	List<Pedido> findByCliente(Cliente cliente);

	@Query(" select p from Pedido p left join fetch p.itens where p.id =:id ")//esta pegando todos os itens relcaionados ao item tanto como cliente id preço etc.. e trazendo 
	Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
	
}
