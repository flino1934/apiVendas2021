package com.nava.vendas.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.nava.vendas.domain.entity.Usuario;
import com.nava.vendas.rest.dto.CredenciaistDto;
import com.nava.vendas.rest.dto.TokenDto;
import com.nava.vendas.security.jwt.Jwtservice;
import com.nava.vendas.service.rest.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	private final UsuarioServiceImpl usuarioService;
	private final PasswordEncoder passwordEncoder;//estamos passando para poder incriptografar a senha
	private final Jwtservice jwtservice;
	
	
	
	public UsuarioController(UsuarioServiceImpl usuarioService, PasswordEncoder passwordEncoder,
			Jwtservice jwtservice) {
		
		this.usuarioService = usuarioService;
		this.passwordEncoder = passwordEncoder;
		this.jwtservice = jwtservice;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario salvar( @RequestBody Usuario usuario) {//vai salvar o usuario  esse metodo vai apenas chamar o metodo responsavel que vem de UsuarioServiceImpl
		
		String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());//esta criptografando a senha 
		usuario.setSenha(senhaCriptografada);//pegou asenha criptografada e passou para a classe Usuario pois quando usuarioServiceImpl solicitar a senha ele vai pegar essa
		return usuarioService.salvar(usuario);
		
	}
	
	@PostMapping("/auth")
	public TokenDto autenticar(@RequestBody CredenciaistDto credenciaistDto) {
		
		try {
			
			Usuario usuarioAutenticado = Usuario.builder()
					.login(credenciaistDto.getLogin())
					.senha(credenciaistDto.getSenha()).build();
			String token = jwtservice.gerarToken(usuarioAutenticado);
			return new TokenDto(usuarioAutenticado.getLogin(),token);
			
		}catch (UsernameNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,e.getMessage());
		}
		
	}
	
}
