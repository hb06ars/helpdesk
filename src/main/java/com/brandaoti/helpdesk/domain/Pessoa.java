package com.brandaoti.helpdesk.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.brandaoti.helpdesk.domain.enums.Perfil;

// Abstract porque nao sera usado essa classe diretamente, sera usado ou tecnico ou cliente
// protect porque todos os tecnicos e clientes que herdarem a pessoa poderao acessar os dados.

public abstract class Pessoa {
	
	protected Integer id;
	protected String nome;
	protected String cpf;
	protected String email;
	protected String senha;
	protected Set<Integer> perfis = new HashSet<>(); //Não permite 2 valores iguais na lista.
	protected LocalDate dataCriacao = LocalDate.now();
	
	
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getCpf() {
		return cpf;
	}



	public void setCpf(String cpf) {
		this.cpf = cpf;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getSenha() {
		return senha;
	}



	public void setSenha(String senha) {
		this.senha = senha;
	}



	public Set<Perfil> getPerfis() {
		//Como um for...
		return perfis.stream().map(x -> Perfil.toenum(x)).collect(Collectors.toSet());
	}



	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}



	public LocalDate getDataCriacao() {
		return dataCriacao;
	}



	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}



	//Construtor da classe com parâmetros
	public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		addPerfil(Perfil.CLIENTE); // Aqui todo usuario criado vai ter o Perfil de Cliente pelo menos.
	}


	//Construtor da classe sem parâmetros
	public Pessoa() {
		super();
		addPerfil(Perfil.CLIENTE); // Aqui todo usuario criado vai ter o Perfil de Cliente pelo menos.
	}


	//Generate Hashcode e equals Serve para fazer comparação de objeto por valor dele, exemplo CPF ou ID.
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	//Generate Hashcode e equals Serve para fazer comparação de objeto por valor dele, exemplo CPF ou ID.
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
}
