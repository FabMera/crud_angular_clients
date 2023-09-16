package com.angular.spring.backend.apirest.springbootbackendapirest.controllers;


import com.angular.spring.backend.apirest.springbootbackendapirest.error.ClienteNotFoundException;
import com.angular.spring.backend.apirest.springbootbackendapirest.models.entity.Cliente;
import com.angular.spring.backend.apirest.springbootbackendapirest.models.services.IClienteService;
import jakarta.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {


    private IClienteService clienteService;

    public ClienteRestController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    //Metodo que trae todos los clientes
    @GetMapping("/clientes")
    public List<Cliente> index() {
        return clienteService.findAllClients();
    }

    //Metodo que trae todos los clientes paginados
    @GetMapping("/clientes/page/{page}")
    public Page<Cliente> indexPage(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        return clienteService.findAllClientsPage(pageable);
    }

    //Metodo para mostrar un cliente por id
    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> showById(@PathVariable Long id) throws ClienteNotFoundException {
        Optional<Cliente> cliente = clienteService.findClientById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.orElseThrow());
        }
        throw new ClienteNotFoundException("Cliente :" +" " + " " + id + "no encontrado");
    }

    //Metodo para crear un cliente
    @PostMapping("/clientes")
    public ResponseEntity<?> create(@RequestBody @Valid Cliente cliente, BindingResult binding) {
        Cliente clienteNew = null;
        Map<String, Object> response = new HashMap<>();
        if (binding.hasErrors()) {
            return validation(binding);
        }
        try {
            clienteNew = clienteService.saveClient(cliente);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente ha sido creado con exito");
        response.put("cliente", clienteNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    private ResponseEntity<?> validation(BindingResult binding) {
        Map<String, Object> response = new HashMap<>();
        List<String> errors = binding.getFieldErrors().stream().map(err ->
                "El campo '" + err.getField() + "' " + err.getDefaultMessage()).collect(Collectors.toList());
        response.put("errors", errors);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }

    //Metodo para EDITAR un cliente por id
    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable Long id) throws ClienteNotFoundException {
        Optional<Cliente> clienteOp = clienteService.findClientById(id);
        if (clienteOp.isPresent()) {
            //Relaciono el cliente que viene por parametro con el cliente que se encuentra en la base de datos.
            Cliente clienteActual = clienteOp.orElseThrow();
            clienteActual.setNombre(cliente.getNombre());
            clienteActual.setApellido(cliente.getApellido());
            clienteActual.setEmail(cliente.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.saveClient(clienteActual));
        }
        throw new ClienteNotFoundException("Cliente :" + "" + id + " " + "no encontrado");
    }

    //Metodo para ELIMINAR un cliente por id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws ClienteNotFoundException {
        Map<String, Object> response = new HashMap<>();
        Optional<Cliente> clienteOp = clienteService.findClientById(id);
        if (clienteOp.isPresent()) {
            clienteService.deleteClient(id);
            response.put("mensaje", "Cliente eliminado con exito");
            response.put("cliente", clienteOp.orElseThrow());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
        }
        throw new ClienteNotFoundException("Cliente :" + " " + id + " " + "no encontrado");
    }

    //Metodo para retornar un mensaje de error cuando no se encuentra un cliente por id.

}
