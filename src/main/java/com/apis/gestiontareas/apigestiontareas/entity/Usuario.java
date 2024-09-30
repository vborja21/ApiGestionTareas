package com.apis.gestiontareas.apigestiontareas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Entity
@Data

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer idUsuario;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @NotBlank(message = "El email es obligatorio")
    private String email;

    @NotNull(message = "El celular es obligatorio")
    private Integer celular;

    @NotNull(message = "El dni es obligatorio")
    @Column(unique = true)
    private Integer dni;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    @Column(unique = true)
    @NotBlank(message = "El login es obligatorio")
    @Size(min = 5, max = 30, message = "El login debe tener entre 5 y 30 caracteres")
    private String login;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    private Boolean admin;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "idUsuario"), inverseJoinColumns = @JoinColumn(name = "idRol"))
    private Set<Rol> roles;

}
