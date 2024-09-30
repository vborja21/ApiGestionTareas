package com.apis.gestiontareas.apigestiontareas.serviceImpl;

import com.apis.gestiontareas.apigestiontareas.entity.Rol;
import com.apis.gestiontareas.apigestiontareas.repository.RepositoryRol;
import com.apis.gestiontareas.apigestiontareas.service.RolService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class RolServiceImpl implements RolService {

    private RepositoryRol rolRepository;


    @Override
    public List<Rol> findAll() {

        return rolRepository.findAll();

    }

    @Override
    public Optional<Rol> findByNombreRol(String nombre) {

        return rolRepository.findByNombreRol(nombre);

    }
}
