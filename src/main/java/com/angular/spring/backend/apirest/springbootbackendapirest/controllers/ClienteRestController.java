package com.angular.spring.backend.apirest.springbootbackendapirest.controllers;


import com.angular.spring.backend.apirest.springbootbackendapirest.models.entity.Cliente;
import com.angular.spring.backend.apirest.springbootbackendapirest.models.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/clientes")
public class ClienteRestController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/listar")
    public List<Cliente> index() {
        return clienteService.findAllClients();
    }

    //Metodo para mostrar un usuario por id
    @GetMapping("/listar/{id}")
    public Cliente showById(@PathVariable Long id) {
        return clienteService.findClientById(id).orElse(null);
    }

    //Metodo para crear un cliente
    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente create(@RequestBody Cliente cliente) {
        return clienteService.saveClient(cliente);
    }

    //Metodo para EDITAR un cliente por id
    @PutMapping("/clientes/{id}")
    public Cliente update(@RequestBody Cliente cliente, @PathVariable Long id) {
        Cliente clienteActual = clienteService.findClientById(id).orElse(null);
        clienteActual.setNombre(cliente.getNombre());
        clienteActual.setApellido(cliente.getApellido());
        clienteActual.setEmail(cliente.getEmail());
        return clienteService.saveClient(clienteActual);
    }

    @DeleteMapping("/clientes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        clienteService.deleteClient(id);
    }

}
