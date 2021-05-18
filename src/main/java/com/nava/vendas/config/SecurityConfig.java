package com.nava.vendas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nava.vendas.service.rest.impl.UsuarioServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();// toda vez que o usuario passar uma senha ele vai gerar um hash diferente da senha
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {//esse metodo vai gerenciar os objetos de autenticação no contexto do security, aqui será configurado a autenticação exemplo qual vai ser o usuario qual a senha de onde vem 
		
		
		/*ese trecho do código se refere ao usuario em memória não é muito Utilizado
		auth.inMemoryAuthentication()//vai armazenar os ddados em memoria
						.passwordEncoder(passwordEncoder())//vai comparar a senha do usuario
						.withUser("f.lino")//definiu o usuario como f.lino
						.password(passwordEncoder().encode("123"))	//definiu a senha 
						.roles("USER","ADMIN");//são os perfis de usuario*/
		
		auth
			.userDetailsService(usuarioService)//estou falando que o usuario vai vir de UserDetailService
			.passwordEncoder(passwordEncoder());//vai comparar a senha do usuario
		
		//os usuario serão carregado dentro do UsuarioServiceImpl ja as senhas serão verificadas nesse metodo
			
		
	}

	@Override
	protected void configure( HttpSecurity http) throws Exception {//Estaá responsavel pela autorização vai ppegar o usuario autenticado e verificar se tem autenticação, vai ver quem tem autorização aonde e ao que vai configurar as rotas
	
		http
			.csrf().disable()//estamos desabilitando csrf pois ele é para aplicações WEB que tem front End como estamos trabalhando apenas com o statement vamos desabilitar
			.authorizeRequests()
				.antMatchers("/api/clientes/**")//estou passando a URL da API
					.hasAnyRole("USER","ADMIN") //estou falando quais perfis tem acesso a essa URL são mais de um perfil que tem acesso a esse endpoint
			//.authenticated()//esta falando que qualquer usuaario autenticado terá acesso
				.antMatchers("/api/produtos/**")
					.hasRole("ADMIN")//estou falando que o usuario com perfil USER tem acesso a esse 
				.antMatchers("/api/pedidos/**")
					.hasRole("USER")
				.antMatchers(HttpMethod.POST,"/api/usuarios/**")
					.permitAll()
				.anyRequest().authenticated()	
			.and()//volta para raiz
				.httpBasic();//vai permitir que passe as requisições através dos haders vai mostrar um formulario basico
	}

}
