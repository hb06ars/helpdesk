package com.brandaoti.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.brandaoti.helpdesk.domain.Chamado;
import com.brandaoti.helpdesk.domain.Cliente;
import com.brandaoti.helpdesk.domain.Tecnico;
import com.brandaoti.helpdesk.domain.enums.Perfil;
import com.brandaoti.helpdesk.domain.enums.Prioridade;
import com.brandaoti.helpdesk.domain.enums.Status;
import com.brandaoti.helpdesk.repositories.ChamadoRepository;
import com.brandaoti.helpdesk.repositories.PessoaRepository;

@Service //Essa classe serve para injeção de dependencias, o spring cria, destroi, etc
public class DBService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public void instanciaDB() {
		Tecnico t1 = new Tecnico(null, "Henrique Brandão", "98062783057", "teste@teste.com", encoder.encode("123"));
		t1.addPerfil(Perfil.TECNICO);
		
		Cliente cli1 = new Cliente(null, "Linus", "99333717030", "cliente@cliente.com", encoder.encode("123"));
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", t1, cli1);
		
		pessoaRepository.saveAll(Arrays.asList(t1));
		pessoaRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
		
	}
	
}
