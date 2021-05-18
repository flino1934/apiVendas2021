package com.nava.vendas.security.jwt;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import com.nava.vendas.VendasApplication;
import com.nava.vendas.domain.entity.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class Jwtservice {

	@Value("${security.jwt.expiracao}") 
	private String expiracao;
	@Value("security.jwt.chave-assinatura")
	private String chaveAssinatura;

	public String gerarToken(Usuario usuario) {//depois do usuario autenticado vai vir para esse metodo que vai gerar o token

		long exString = Long.valueOf(expiracao);
		LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(exString);//esta pegando a hora atual e somando 30 min
		Date data = Date.from(dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant());//definiu o horario local do id como padrão
		
//		HashMap<String, Object> claims = new HashMap<>();
//		claims.put("email do usuario", "f.lino1934@hotmail.com");
//		claims.put("roles", "ADMIN");
		//poderia passar o claims que seria um tipo de informações adicionais 
		
		return Jwts
					.builder()
					.setSubject(usuario.getLogin())//faz parte do Playload, que são as informações de autenticação exemplo usuario, chave etc..
					.setExpiration(data)//esta passando a hora de expiração
					//.setClaims(claims)
					.signWith(SignatureAlgorithm.HS512,chaveAssinatura)//esta fazendo a assinatura
					.compact();//Compacta em String
	}
	
	private Claims obterClaims(String token) throws ExpiredJwtException{
		return Jwts
					.parser()//vaoi decodificar o token
					.setSigningKey(chaveAssinatura)//esta passando a chave de assinatura do token
					.parseClaimsJws(token)
					.getBody();
	}
	
	public String obterLoginUsuario(String token) throws ExpiredJwtException{
		
		return (String) obterClaims(token).getSubject();
		
	}
	
	public boolean tokenValido(String token) {
		
		try {
			Claims claims = obterClaims(token);
			Date dateExpiration = claims.getExpiration();
			LocalDateTime localDateTime = dateExpiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			return localDateTime.now().isAfter(localDateTime);
		}catch(Exception e) {
			return false;
		}
		
	}
	
	public static void main(String[] args) {
		ConfigurableApplicationContext contexto = SpringApplication.run(VendasApplication.class);
		Jwtservice jwtservice = contexto.getBean(Jwtservice.class);
		Usuario usuario = Usuario.builder().login("f.lino").build();
		String token = jwtservice.gerarToken(usuario);
		System.out.println(token);
		
		boolean isTokenValido = jwtservice.tokenValido(token);
		System.out.println("o token esta valido ?"+isTokenValido);
		System.out.println(jwtservice.obterLoginUsuario(token));
		
	}

}
