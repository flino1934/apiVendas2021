package com.nava.vendas.rest.controller;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {

	private List<String> errors;

	public ApiErrors() {
		// TODO Auto-generated constructor stub
	}
	
	public ApiErrors(String mensagemErro) {
		this.errors = Arrays.asList(mensagemErro);//vai receber o erro e trasnfortmar em um ArrayList
	}

	public ApiErrors(List<String> errors) {

		this.errors = errors;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "ApiErrors [errors=" + errors + "]";
	}

}
