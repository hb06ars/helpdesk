package com.brandaoti.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brandaoti.helpdesk.domain.Pessoa;
import com.brandaoti.helpdesk.domain.Tecnico;
import com.brandaoti.helpdesk.dtos.TecnicoDTO;
import com.brandaoti.helpdesk.exceptions.DataIntegrityViolationException;
import com.brandaoti.helpdesk.exceptions.ObjectNotFoundException;
import com.brandaoti.helpdesk.repositories.PessoaRepository;
import com.brandaoti.helpdesk.repositories.TecnicoRepository;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id); //Optional, pode ou nao encontrar o obj
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id) );
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		objDTO.setId(null);
		validaPorCpfEEmail(objDTO);
		Tecnico newObj = new Tecnico(objDTO); 
		return repository.save(newObj);
	}

	private void validaPorCpfEEmail(TecnicoDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			//Caso haja um CPF mas nao é esse CPF no banco (CPF Duplicado)
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
		}
		
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			//Caso haja um CPF mas nao é esse CPF no banco (CPF Duplicado)
			throw new DataIntegrityViolationException("Email já cadastrado no sistema");
		}
	}

	
	
}
