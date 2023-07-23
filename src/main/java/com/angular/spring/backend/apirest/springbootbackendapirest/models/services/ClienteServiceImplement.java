package com.angular.spring.backend.apirest.springbootbackendapirest.models.services;

import com.angular.spring.backend.apirest.springbootbackendapirest.models.dao.IClienteDao;
import com.angular.spring.backend.apirest.springbootbackendapirest.models.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImplement implements IClienteService {

    @Autowired
    private IClienteDao clienteDao;
    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAllClients() {
        return (List<Cliente>)clienteDao.findAll();
    }

    @Override
    @Transactional
    public Cliente saveClient(Cliente cliente) {
        return clienteDao.save(cliente);
    }

    @Override
    @Transactional
    public void deleteClient(Long id) {
        clienteDao.deleteById(id);

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> findClientById(Long id) {
        return clienteDao.findById(id);
    }
}
