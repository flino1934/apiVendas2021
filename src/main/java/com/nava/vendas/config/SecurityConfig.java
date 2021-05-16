package com.nava.vendas.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {//esse metodo vai gerenciar os objetos de autenticação no contexto do security, aqui será configurado a autenticação exemplo qual vai ser o usuario qual a senha de onde vem 
		// TODO Auto-generated method stub
		super.configure(auth);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {//Estaá responsavel pela autorização vai ppegar o usuario autenticado e verificar se tem autenticação, vai ver quem tem autorização aonde e ao que 
		// TODO Auto-generated method stub
		super.configure(http);
	}

}
