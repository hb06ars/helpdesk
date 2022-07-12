package com.brandaoti.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brandaoti.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

}
