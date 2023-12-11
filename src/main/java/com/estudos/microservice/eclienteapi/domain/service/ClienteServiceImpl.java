package com.estudos.microservice.eclienteapi.domain.service;

import com.estudos.microservice.eclienteapi.domain.exception.BusinessException;
import com.estudos.microservice.eclienteapi.domain.exception.NotFoundException;
import com.estudos.microservice.eclienteapi.domain.model.Cliente;
import com.estudos.microservice.eclienteapi.domain.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService{


    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional
    public Cliente create(Cliente cliente) {
        Optional<Cliente> clienteOptional =
                clienteRepository.findByNomeIgnoreCase(cliente.getNome());

        if (clienteOptional.isPresent()) {
            throw new BusinessException("Já existe um cliente com esse nome.");
        }

        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente findById(Long idCliente) {
        return clienteRepository.findById(idCliente)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado."));
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente update(Long idCliente, Cliente cliente) {
        return null;
    }

    @Override
    public void delete(Long idCliente) {
        Cliente agrupamentoDeLocais = findById(idCliente);
        clienteRepository.delete(agrupamentoDeLocais);
    }
}
