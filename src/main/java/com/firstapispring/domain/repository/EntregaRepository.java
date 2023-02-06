package com.firstapispring.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.firstapispring.domain.model.Entrega;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega,Long> {

    Entrega findByClienteId(Long clienteId);


    
}
