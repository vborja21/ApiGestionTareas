package com.apis.gestiontareas.apigestiontareas.jwt;

import com.apis.gestiontareas.apigestiontareas.entity.Usuario;
import com.apis.gestiontareas.apigestiontareas.repository.RepositoryAuthUsuario;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class DetalleUsuarioService implements UserDetailsService {

    private RepositoryAuthUsuario repositoryAuthUsuario;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuarioOp = repositoryAuthUsuario.findByUsername(username);
        if (usuarioOp.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Usuario %s no encontrado en el sistema", username));
        }

        Usuario usuario = usuarioOp.orElseThrow();

        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                .collect(Collectors.toList());

        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getActivo(), true, true, true, authorities);


    }
}
