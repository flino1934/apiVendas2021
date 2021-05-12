package com.nava.vendas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nava.vendas.domain.entity.Produto;

@Repository
public interface ProdutosRepository extends JpaRepository<Produto, Integer>{

}
