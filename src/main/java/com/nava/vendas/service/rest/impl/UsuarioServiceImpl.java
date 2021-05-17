package com.nava.vendas.service.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UserDetailsService{//esta implementando uma interface padrão já existente no java chamada de UserDetailsService
	
	@Autowired
	private PasswordEncoder encoder;//vai chamar o mesmo encoder que foi passado na classe SecurityConfig e chamar
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {//define os usuarios através do carragemanto de BD
		//no primeiro instante não estamos consultando a BD para trazer o usuario
		return User
					.builder()
					.username("f.lino")//esta passando o usuario
					.password(encoder.encode("123"))//esta passando a senha 
					.roles("USER","ADMIN")//os papei que cada um exerce dentro do sistema
					.build();
	}

}
