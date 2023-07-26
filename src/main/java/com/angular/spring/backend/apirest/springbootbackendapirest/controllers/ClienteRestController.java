package com.angular.spring.backend.apirest.springbootbackendapirest.controllers;


import com.angular.spring.backend.apirest.springbootbackendapirest.models.entity.Cliente;
import com.angular.spring.backend.apirest.springbootbackendapirest.models.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/clientes")
public class ClienteRestController {

    @Autowired
    private IClienteService clienteService;

    //Metodo que trae todos los clientes
    @GetMapping("/listar")
    public List<Cliente> index() {
        return clienteService.findAllClients();
    }

    //Metodo para mostrar un cliente por id
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> showById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.findClientById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.orElseThrow());
        }
        return getApiErrorResponseResponseEntity(id);
    }

    //Metodo para crear un cliente
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody Cliente cliente) {
        Cliente clienteNew = null;
        Map<String, Object> response = new HashMap<>();
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

    //Metodo para EDITAR un cliente por id
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable Long id) {
        Optional<Cliente> clienteOp = clienteService.findClientById(id);
        if (clienteOp.isPresent()) {
            //Relaciono el cliente que viene por parametro con el cliente que se encuentra en la base de datos.
            Cliente clienteActual = clienteOp.orElseThrow();
            clienteActual.setNombre(cliente.getNombre());
            clienteActual.setApellido(cliente.getApellido());
            clienteActual.setEmail(cliente.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.saveClient(clienteActual));
        }
        return getApiErrorResponseResponseEntity(id);
    }

    //Metodo para ELIMINAR un cliente por id
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        clienteService.deleteClient(id);
    }
    //Metodo para retornar un mensaje de error cuando no se encuentra un cliente por id.
    private static ResponseEntity<ApiErrorResponse> getApiErrorResponseResponseEntity(Long id) {
        ApiErrorResponse error = new ApiErrorResponse(404, "Cliente" + " " + id + " " + "no encontrado,NOT_FOUND");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(error);
    }
}
