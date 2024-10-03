package com.apis.gestiontareas.apigestiontareas.controller;

import com.apis.gestiontareas.apigestiontareas.entity.Usuario;
import com.apis.gestiontareas.apigestiontareas.repository.RepositoryAuthUsuario;
import com.apis.gestiontareas.apigestiontareas.service.AuthUsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")

public class AuthUsuarioController {

    private AuthUsuarioService authUsuarioService;

    @PostMapping("/register")

    public ResponseEntity<?> register (@Valid @RequestBody Usuario user, BindingResult result) {

        if (result.hasErrors()) {

            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(err -> {
                errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors);

        }

        user.setAdmin(true);

        return ResponseEntity.status(HttpStatus.CREATED).body(authUsuarioService.guardarUsuario(user));

    }

}
