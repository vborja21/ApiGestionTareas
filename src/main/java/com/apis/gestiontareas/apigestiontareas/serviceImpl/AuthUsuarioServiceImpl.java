package com.apis.gestiontareas.apigestiontareas.serviceImpl;

import com.apis.gestiontareas.apigestiontareas.entity.Rol;
import com.apis.gestiontareas.apigestiontareas.entity.Usuario;
import com.apis.gestiontareas.apigestiontareas.repository.RepositoryAuthUsuario;
import com.apis.gestiontareas.apigestiontareas.repository.RepositoryRol;
import com.apis.gestiontareas.apigestiontareas.service.AuthUsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor

public class AuthUsuarioServiceImpl implements AuthUsuarioService {

    private RepositoryRol rolRepository;
    private PasswordEncoder passwordEncoder;
    private RepositoryAuthUsuario usuarioRepository;


    @Override
    public Usuario guardarUsuario(Usuario usuario) {

        Set<Rol> roles = new HashSet<>();

        if (usuario.getAdmin()) {
            Optional<Rol> optionalRolAdmin = rolRepository.findByNombre("ADMIN");
            optionalRolAdmin.ifPresent(roles::add);

        }

        usuario.setRoles(roles);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setActivo(true);

        return usuarioRepository.save(usuario);


    }
}
