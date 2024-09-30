package com.apis.gestiontareas.apigestiontareas.service;

import com.apis.gestiontareas.apigestiontareas.entity.Rol;

import java.util.List;
import java.util.Optional;

public interface RolService {

    List<Rol> findAll();
    Optional<Rol> findByNombreRol(String nombre);

}
