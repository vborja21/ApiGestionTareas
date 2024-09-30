package com.apis.gestiontareas.apigestiontareas.repository;

import com.apis.gestiontareas.apigestiontareas.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryRol extends JpaRepository<Rol, Long> {

    Optional<Rol> findByNombreRol(String nombre);

}
