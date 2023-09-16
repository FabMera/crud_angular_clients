package com.angular.spring.backend.apirest.springbootbackendapirest.models.services;

import com.angular.spring.backend.apirest.springbootbackendapirest.error.UsuarioNotFoundException;
import com.angular.spring.backend.apirest.springbootbackendapirest.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    List<Usuario> findAllUsers();
    Optional<Usuario> findUserById(Long id) throws UsuarioNotFoundException;
    Usuario saveUser(Usuario user);
    void removeUserById(Long id);
    Optional<Usuario> updateUser(Long id, Usuario user);
}
