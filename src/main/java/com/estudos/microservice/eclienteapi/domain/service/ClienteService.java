package com.estudos.microservice.eclienteapi.domain.service;

import com.estudos.microservice.eclienteapi.domain.model.Cliente;

import java.util.List;

public interface ClienteService {

    Cliente create(Cliente cliente);

    Cliente findById(Long idCliente);

    List<Cliente> findAll();

    Cliente update(Long idCliente, Cliente cliente);

    void delete(Long idCliente);
}
