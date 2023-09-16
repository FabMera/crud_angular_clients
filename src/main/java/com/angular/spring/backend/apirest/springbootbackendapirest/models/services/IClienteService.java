package com.angular.spring.backend.apirest.springbootbackendapirest.models.services;

import com.angular.spring.backend.apirest.springbootbackendapirest.models.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IClienteService {
    public List<Cliente> findAllClients();

    public Cliente saveClient(Cliente cliente);

    public void deleteClient(Long id);

    public Optional<Cliente> findClientById(Long id);

    public Page<Cliente> findAllClientsPage(Pageable pageable);

}
