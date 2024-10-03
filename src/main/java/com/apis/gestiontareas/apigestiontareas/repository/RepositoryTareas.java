package com.apis.gestiontareas.apigestiontareas.repository;

import com.apis.gestiontareas.apigestiontareas.entity.Estado;
import com.apis.gestiontareas.apigestiontareas.entity.Tareas;
import com.apis.gestiontareas.apigestiontareas.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositoryTareas extends JpaRepository<Tareas, Integer> {

    List<Tareas> findByEstado (Estado estado);

    List<Tareas> findByUsuario (Usuario usuario);

    //Filtros adicionales

    //Menor a Mayor

    @Query("SELECT t FROM Tareas t ORDER by t.fechaVencimiento ASC")

    List<Tareas> menorFechaVencimiento();

    //Mayor a Menor

    @Query("SELECT t FROM Tareas t ORDER by t.fechaVencimiento DESC")

    List<Tareas> mayorFechaVencimiento();

    //Titulo

    @Query ("SELECT t FROM Tareas t ORDER by t.titulo ASC")

    List<Tareas> ordenarPorTitulo();

    //Descripcion

    @Query("SELECT t FROM Tareas t ORDER by t.descripcion ASC")

    List<Tareas> ordenarPorDescripcion();

}
