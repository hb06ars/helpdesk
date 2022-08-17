package com.brandaoti.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.brandaoti.helpdesk.domain.Cliente;
import com.brandaoti.helpdesk.domain.Pessoa;
import com.brandaoti.helpdesk.dtos.ClienteDTO;
import com.brandaoti.helpdesk.exceptions.DataIntegrityViolationException;
import com.brandaoti.helpdesk.exceptions.ObjectNotFoundException;
import com.brandaoti.helpdesk.repositories.ClienteRepository;
import com.brandaoti.helpdesk.repositories.PessoaRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id); //Optional, pode ou nao encontrar o obj
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id) );
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		objDTO.setId(null);
		objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		validaPorCpfEEmail(objDTO);
		Cliente newObj = new Cliente(objDTO); 
		return repository.save(newObj);
	}

	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id);
		Cliente oldObj = findById(id);
		if(!objDTO.getSenha().equals(oldObj.getSenha())) {
			objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		}
		validaPorCpfEEmail(objDTO);
		oldObj = new Cliente(objDTO);
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		Cliente obj = findById(id);
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("O técnico possui ordens de serviço e não podem ser deletado!");
		}
		repository.deleteById(id);
	}

	private void validaPorCpfEEmail(ClienteDTO objDTO) {
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
