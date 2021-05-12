package com.nava.vendas;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import com.nava.vendas.domain.entity.Cliente;
import com.nava.vendas.domain.entity.Pedido;
import com.nava.vendas.domain.repository.ClientesRepository;
import com.nava.vendas.domain.repository.PedidoRepository;

@SpringBootApplication
@RestController
public class VendasApplication {

	@Bean
	public CommandLineRunner init(@Autowired ClientesRepository clientesRepository,
			@Autowired PedidoRepository pedidosRepository) {

		return args -> {

			System.out.println("========= Salvando clientes! =========");
			Cliente fulano = new Cliente("Felipe Lino");
			clientesRepository.save(fulano);//esta salavando o cliente

			Pedido p = new Pedido();
			p.setCliente(fulano);// esta pedido como parametro um objeto do tipo Cleiente
			p.setDataPedido(LocalDate.now());
			p.setTotal(BigDecimal.valueOf(100));
			pedidosRepository.save(p);//esta salvando os pedidos

//			Cliente cliente = clientesRepository.findClienteFetchPedidos(fulano.getId());
//			System.out.println(cliente);
//			System.out.println(cliente.getPedido());

			pedidosRepository.findByCliente(fulano).forEach(System.out::println);//esta pedquisando todos os pedidos de um determinado cliente 
			
		};

	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
