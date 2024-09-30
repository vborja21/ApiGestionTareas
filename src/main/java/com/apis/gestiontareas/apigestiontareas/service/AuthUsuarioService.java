package com.apis.gestiontareas.apigestiontareas.service;

import com.apis.gestiontareas.apigestiontareas.entity.Usuario;

import java.util.List;

public interface AuthUsuarioService {

    List<Usuario> listarUsuarios();
    Usuario guardarUsuario(Usuario usuario);

}
