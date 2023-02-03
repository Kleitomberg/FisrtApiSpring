package com.firstapispring.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.firstapispring.domain.model.Cliente;
import com.firstapispring.domain.repository.ClienteRepository;
import com.firstapispring.domain.service.ClienteService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {	
	
	
	private ClienteRepository clienteRepository;

	private ClienteService clienteService;
	
	@GetMapping
	public List<Cliente> clienteList() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{clientId}")
	public ResponseEntity<Cliente> buscarcliente(@PathVariable  Long clientId) {		 
		return clienteRepository.findById(clientId)
				.map(cliente -> ResponseEntity.ok(cliente))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionarCliente(@Valid @RequestBody Cliente cliente) {		
		//return clienteRepository.save(cliente);
		return clienteService.salvar(cliente);
	}
	
	@PutMapping("/{clientId}")
	public ResponseEntity<Cliente> atualizarCliente( @PathVariable Long clientId, @Valid @RequestBody Cliente cliente){
		
		if( !clienteRepository.existsById(clientId) ) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clientId);
		cliente = clienteRepository.save(cliente);
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{clientId}")
	public ResponseEntity<Void> deleteCliente(@PathVariable Long clientId) {
		
		if ( !clienteRepository.existsById(clientId)) {			
			return ResponseEntity.notFound().build();
		}
		///clienteRepository.deleteById(clientId);
		clienteService.excluir(clientId);
		
		return ResponseEntity.noContent().build();

		
	}
	
}
