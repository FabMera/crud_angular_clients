package com.angular.spring.backend.apirest.springbootbackendapirest.controllers;

import com.angular.spring.backend.apirest.springbootbackendapirest.error.UsuarioNotFoundException;
import com.angular.spring.backend.apirest.springbootbackendapirest.models.entity.Usuario;
import com.angular.spring.backend.apirest.springbootbackendapirest.models.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listaUsuarios() {
        return usuarioService.findAllUsers();
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.saveUser(usuario);
    }

    @GetMapping("/{id}")
    public Usuario mostrarUsuarioPorId(@PathVariable Long id) throws UsuarioNotFoundException {
        return usuarioService.findUserById(id).orElseThrow();
    }
}