package com.apis.gestiontareas.apigestiontareas.repository;

import com.apis.gestiontareas.apigestiontareas.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryAuthUsuario extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);

}
