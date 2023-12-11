package com.estudos.microservice.eclienteapi.domain.repository;

import com.estudos.microservice.eclienteapi.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByNomeIgnoreCase(String nome);
}
