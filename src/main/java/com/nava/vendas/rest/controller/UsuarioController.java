package com.nava.vendas.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nava.vendas.domain.entity.Usuario;
import com.nava.vendas.service.rest.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	private final UsuarioServiceImpl usuarioService;
	private final PasswordEncoder passwordEncoder;//estamos passando para poder incriptografar a senha
	
	public UsuarioController(UsuarioServiceImpl usuarioService,PasswordEncoder passwordEncoder) {
		
		this.usuarioService = usuarioService;
		this.passwordEncoder = passwordEncoder;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario salvar( @RequestBody Usuario usuario) {//vai salvar o usuario  esse metodo vai apenas chamar o metodo responsavel que vem de UsuarioServiceImpl
		
		String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());//esta criptografando a senha 
		usuario.setSenha(senhaCriptografada);//pegou asenha criptografada e passou para usuarioServiceImpl pois quando ele solicitar a senha ele vai pegar essa
		return usuarioService.salvar(usuario);
		
	}
	
}
