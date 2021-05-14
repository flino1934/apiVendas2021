package com.nava.vendas.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nava.vendas.exception.RegraNegocioExcption;

@RestControllerAdvice
public class ApplicationControllerAdvice {// esta classe vai fazer tratamento utilizando excptions handlers

	@ExceptionHandler(RegraNegocioExcption.class) // vai marcar esse metodo para ser um tratador de erro
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleRegraNegocioException(RegraNegocioExcption ex) {
		String mensagemErro = ex.getMessage();
		return new ApiErrors();
	}

}
