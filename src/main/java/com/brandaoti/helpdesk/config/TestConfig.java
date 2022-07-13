package com.brandaoti.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.brandaoti.helpdesk.services.DBService;

@Configuration // Classe de configuração
@Profile("testesHenrique") //test que vem do application-test.properties depois do traço
public class TestConfig {
	
	@Autowired
	private DBService dBService;
	
	//Toda vez que o test do aplication properties estiver ativo vai chamar de forma automatica (BEAN) esse metodo de instanciaDB
	@Bean
	public void instanciaDB() {
		this.dBService.instanciaDB();
	}
	
	
	
}
