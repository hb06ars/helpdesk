package com.brandaoti.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brandaoti.helpdesk.domain.Tecnico;
import com.brandaoti.helpdesk.exceptions.ObjectNotFoundException;
import com.brandaoti.helpdesk.repositories.TecnicoRepository;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository repository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id); //Optional, pode ou nao encontrar o obj
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id) );
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}
	
}
