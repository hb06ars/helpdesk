package com.brandaoti.helpdesk.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brandaoti.helpdesk.domain.Tecnico;
import com.brandaoti.helpdesk.services.TecnicoService;

@RestController
@RequestMapping(value="/tecnicos")
public class TecnicoResources {
	
	@Autowired
	private TecnicoService service;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Tecnico> findbyId(@PathVariable Integer id){
		Tecnico obj = service.findById(id);
		return ResponseEntity.ok(obj);
	}
	
	
}
