package com.angular.spring.backend.apirest.springbootbackendapirest.models.services;

import com.angular.spring.backend.apirest.springbootbackendapirest.error.UsuarioNotFoundException;
import com.angular.spring.backend.apirest.springbootbackendapirest.models.dao.IUsuarioRepository;
import com.angular.spring.backend.apirest.springbootbackendapirest.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UsuarioServiceImplement implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAllUsers() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findUserById(Long id) throws UsuarioNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.orElseThrow();
            return Optional.of(usuario);
        }
        throw new UsuarioNotFoundException("Usuario " + "" + id + "no encontrado");
    }

    @Override
    @Transactional
    public Usuario saveUser(Usuario user) {
        String passwordBC = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordBC);
        return usuarioRepository.save(user);
    }

    @Override
    public void removeUserById(Long id) {

    }

    @Override
    public Optional<Usuario> updateUser(Long id, Usuario user) {
        return Optional.empty();
    }
}
