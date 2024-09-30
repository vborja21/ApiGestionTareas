package com.apis.gestiontareas.apigestiontareas.util;

import com.apis.gestiontareas.apigestiontareas.entity.Rol;
import com.apis.gestiontareas.apigestiontareas.repository.RepositoryRol;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor

public class DataInitializer {

    private RepositoryRol rolRepository;

    @PostConstruct

    public void intit() {

        if (rolRepository.count() == 0) {
            rolRepository.save(new Rol("ADMIN"));
        }
    }

}
