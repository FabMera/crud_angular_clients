package com.angular.spring.backend.apirest.springbootbackendapirest.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "usuarios")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 20)
    private String username;
    @Column(length = 60)
    private String password;
    private Boolean enabled;

    //Carga perezosa (Lazy) para que no se cargue todo de una vez.

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //Se crea una tabla intermedia para la relacion muchos a muchos entre usuarios y roles bidireccional.
    @JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id", "role_id"})})
    private List<Role> roles;

    @Serial
    private static final long serialVersionUID = 1L;
}
