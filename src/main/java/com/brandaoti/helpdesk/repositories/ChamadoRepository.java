package com.brandaoti.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brandaoti.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{

}