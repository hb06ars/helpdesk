package com.brandaoti.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brandaoti.helpdesk.domain.Chamado;
import com.brandaoti.helpdesk.exceptions.ObjectNotFoundException;
import com.brandaoti.helpdesk.repositories.ChamadoRepository;

@Service
public class ChamadoService {
	
	@Autowired
	private ChamadoRepository repository;
	
	public Chamado findbyId(Integer id) {
		Optional<Chamado> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado ID: " + id));
	}

	public List<Chamado> findAll() {
		return repository.findAll();
	}
	
	
}
