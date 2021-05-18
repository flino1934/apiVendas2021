package com.nava.vendas.service.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nava.vendas.domain.entity.Usuario;
import com.nava.vendas.domain.repository.UsuarioRepository;
import com.nava.vendas.exception.SenhaInvalidaException;

@Service
public class UsuarioServiceImpl implements UserDetailsService{//esta implementando uma interface padrão já existente no java chamada de UserDetailsService
	
	@Autowired
	private PasswordEncoder encoder;//vai chamar o mesmo encoder que foi passado na classe SecurityConfig e chamar
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		
		return usuarioRepository.save(usuario);//esta chamando o metodo que vem de usuarioRepository
		
	}
	
	public UserDetails autenticar(Usuario usuario) {
		
		UserDetails user = loadUserByUsername(usuario.getLogin());
		boolean senhasBatem = encoder.matches(usuario.getSenha(), user.getPassword());
		
		if (senhasBatem) {
			return user;
		}
		throw new SenhaInvalidaException();
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {//define os usuarios através do carragemanto de BD
		/*no primeiro instante não estamos consultando a BD para trazer o usuario
		return User
					.builder()
					.username("")//esta passando o usuario
					.password(encoder.encode("123"))//esta passando a senha 
					.roles("USER","ADMIN")//os papei que cada um exerce dentro do sistema
					.build();*/
		
		Usuario usuario = usuarioRepository
				.findByLogin(username).orElseThrow(
						()-> new UsernameNotFoundException("Usuario não encontrado na base de dados"));
		
		String[] roles = usuario.isAdmin() ? new String [] {"USER","ADMIN"} : new String[] {"USER"};
		
		return User
					.builder()
					.username(usuario.getLogin())//vai buscar o login
					.password(usuario.getSenha())//vai buscar a senha incriptografada que usuarioControler esta setando na classe Usuario
					.roles(roles)
					.build();
		
	}

}
