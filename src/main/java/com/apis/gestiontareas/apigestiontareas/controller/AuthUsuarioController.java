package com.apis.gestiontareas.apigestiontareas.controller;

import com.apis.gestiontareas.apigestiontareas.entity.Usuario;
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

    @GetMapping("/listarUsuarios")

    public ResponseEntity<?> listarUsuarios() {

        return new ResponseEntity<>(authUsuarioService.listarUsuarios(), HttpStatus.OK);

    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody Usuario user, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(authUsuarioService.guardarUsuario(user));
    }

    @PostMapping("/register")

    public ResponseEntity<?> register (@Valid @RequestBody Usuario user, BindingResult result) {

        user.setAdmin(true);
        return create(user, result);

    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }


}
