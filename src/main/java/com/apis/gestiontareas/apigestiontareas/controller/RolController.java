package com.apis.gestiontareas.apigestiontareas.controller;

import com.apis.gestiontareas.apigestiontareas.entity.Rol;
import com.apis.gestiontareas.apigestiontareas.service.RolService;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.IdGeneratorType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RequestMapping("/rol")
@RestController

public class RolController {

    private RolService rolService;

    @GetMapping("/findAll")

    public List<Rol> findAll() {

        return rolService.findAll();

    }

    @GetMapping("/nombre")

    public Optional<Rol> findByRol(String nombre) {

        return rolService.findByNombreRol(nombre);

    }

}
