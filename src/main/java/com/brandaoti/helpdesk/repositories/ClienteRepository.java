package com.brandaoti.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brandaoti.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}