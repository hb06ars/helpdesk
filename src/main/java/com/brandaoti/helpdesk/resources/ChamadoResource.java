package com.brandaoti.helpdesk.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brandaoti.helpdesk.domain.Chamado;
import com.brandaoti.helpdesk.dtos.ChamadoDTO;
import com.brandaoti.helpdesk.services.ChamadoService;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {
	
	@Autowired
	private ChamadoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
		Chamado obj = service.findbyId(id);
		return ResponseEntity.ok().body(new ChamadoDTO(obj)); // Vai retornar um objeto DTO do chamado.
	}
	
	
}
