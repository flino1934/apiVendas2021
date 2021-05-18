package com.nava.vendas.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nava.vendas.service.rest.impl.UsuarioServiceImpl;

public class JwtAuthFilter extends OncePerRequestFilter{
	
	private Jwtservice jwtService;
	private UsuarioServiceImpl usuarioService;
	
	

	public JwtAuthFilter(Jwtservice jwtService, UsuarioServiceImpl usuarioService) {
		
		this.jwtService = jwtService;
		this.usuarioService = usuarioService;
	}



	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest,
									HttpServletResponse httpServletResponse,
									FilterChain filterChain)
									throws ServletException, IOException {
							
		String authorization = httpServletRequest.getHeader("Authorization");
		
		if (authorization != null && authorization.startsWith("Bearer")) {
			String token = authorization.split(" ")[1];// ele vai retirar o Bearer e colocar na posição 0 do vetor e pegar o tokenn e armazenar na posição 1 do vetor 
			boolean isValid = jwtService.tokenValido(token);// vai pegar o valor do token e dizer se é valido ou não
			
			if (isValid) {
				String loginUsuario = jwtService.obterLoginUsuario(token);
				UserDetails usuario = usuarioService.loadUserByUsername(loginUsuario);
				UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(usuario, null,usuario.getAuthorities());
				user.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
				SecurityContextHolder.getContext().setAuthentication(user);
			}

		}

		filterChain.doFilter(httpServletRequest, httpServletResponse);
		
	}

}
