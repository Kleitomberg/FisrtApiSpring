package com.firstapispring.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.firstapispring.domain.exeption.NegocioException;
import com.firstapispring.domain.model.Cliente;
import com.firstapispring.domain.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;



    @Transactional
    public Cliente buscar(Long clienteID){
        return clienteRepository.findById(clienteID)
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));

    }

    public void excluir(Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            //throw new NegocioException("Cliente não encontrado");
        }
        clienteRepository.deleteById(clienteId);
    }

    @Transactional
    public Cliente salvar(Cliente cliente) {

        //email duplicado

        boolean emailExists = clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
        
        if (emailExists) {
           throw new NegocioException("Já existe um cliente cadastrado com este e-mail"); 
        }
        
        return clienteRepository.save(cliente);
    }

    
}
