package com.nava.vendas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import com.nava.vendas.domain.entity.Cliente;
import com.nava.vendas.domain.repository.ClientesRepository;

@SpringBootApplication
@RestController
public class VendasApplication {

	@Bean
	public CommandLineRunner init (@Autowired ClientesRepository clientesRepository) {
		
		return args ->{
			System.out.println("Salvando clientes!");
			clientesRepository.salvar(new Cliente( "Felipe Lino"));
			clientesRepository.salvar(new Cliente( "Sarah Lino"));
		};
		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
