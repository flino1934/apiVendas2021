package com.nava.vendas;

import java.util.List;

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
			
			System.out.println("========= Salvando clientes! =========");
			clientesRepository.salvar(new Cliente( "Felipe Lino"));
			clientesRepository.salvar(new Cliente( "Sarah Lino"));
		
			System.out.println("Todos Clientes ============");
			List<Cliente> todosClientes = clientesRepository.obterTodos();
			todosClientes.forEach(System.out::println);
			
			System.out.println("========== Update Cliente ========");
			todosClientes.forEach(c ->{//entende que o cliente vai ser nomeado de c pois ele chama a lista de cliente como ("todosClientes") que é do tipo Cliente
				c.setNome(c.getNome() + " atualizado");
				clientesRepository.atualizar(c);
			});
			
			System.out.println("======== Mostrar todos ==========");
			todosClientes = clientesRepository.obterTodos();// estou falando que todosClientes vai receber o metodo obterTodos que é do tipo lista asssim como todosClientes
			todosClientes.forEach(System.out::println);
			
			System.out.println("====== Buscando Cliente ==========");
			clientesRepository.buscarPorNome("Fel").forEach(System.out::println);
			
		};
		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
