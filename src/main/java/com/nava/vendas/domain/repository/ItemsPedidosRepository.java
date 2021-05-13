package com.nava.vendas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nava.vendas.domain.entity.ItemPedido;

public interface ItemsPedidosRepository extends JpaRepository<ItemPedido, Integer>{

}
