package com.brandaoti.helpdesk;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.brandaoti.helpdesk.domain.Chamado;
import com.brandaoti.helpdesk.domain.Cliente;
import com.brandaoti.helpdesk.domain.Tecnico;
import com.brandaoti.helpdesk.domain.enums.Perfil;
import com.brandaoti.helpdesk.domain.enums.Prioridade;
import com.brandaoti.helpdesk.domain.enums.Status;
import com.brandaoti.helpdesk.repositories.ChamadoRepository;
import com.brandaoti.helpdesk.repositories.ClienteRepository;
import com.brandaoti.helpdesk.repositories.TecnicoRepository;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Tecnico t1 = new Tecnico(null, "Henrique Brandão", "22233344455", "teste@teste.com", "123");
		t1.addPerfil(Perfil.TECNICO);
		
		Cliente cli1 = new Cliente(null, "Linus", "1112222211111", "cliente@cliente.com", "123");
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", t1, cli1);
		
		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
		
		
		
		
	}

}
