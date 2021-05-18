package com.nava.vendas;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import com.nava.vendas.domain.entity.Usuario;

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
		return Jwts
					.builder()
					.setSubject(usuario.getLogin())//faz parte do Playload, que são as informações de autenticação exemplo usuario, chave etc..
					.setExpiration(data)//esta passando a hora de expiração
					.signWith(SignatureAlgorithm.HS512,chaveAssinatura)//esta fazendo a assinatura
					.compact();//Compacta em String
	}
	
	public static void main(String[] args) {
		ConfigurableApplicationContext contexto = SpringApplication.run(VendasApplication.class);
		Jwtservice jwtservice = contexto.getBean(Jwtservice.class);
		Usuario usuario = Usuario.builder().login("f.lino").build();
		String token = jwtservice.gerarToken(usuario);
		System.out.println(token);
		
	}

}
