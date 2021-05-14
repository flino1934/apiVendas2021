package com.nava.vendas.rest.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nava.vendas.exception.PedidoNaoEncontradoException;
import com.nava.vendas.exception.RegraNegocioExcption;

@RestControllerAdvice
public class ApplicationControllerAdvice {// esta classe vai fazer tratamento utilizando excptions handlers

	@ExceptionHandler(RegraNegocioExcption.class) // vai marcar esse metodo para ser um tratador de erro
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleRegraNegocioException(RegraNegocioExcption ex) {
		String mensagemErro = ex.getMessage();
		return new ApiErrors();
	}
	
	@ExceptionHandler(PedidoNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors handlePedidoNotFoundException( PedidoNaoEncontradoException ex) {
		
		return new ApiErrors(ex.getMessage());
		
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleMethodNotValidException( MethodArgumentNotValidException ex) {
		List<String> erros = ex.getBindingResult().getAllErrors().stream().map(erro -> erro.getDefaultMessage()).collect(Collectors.toList());
		
		return new ApiErrors(erros);
	}
	
}
