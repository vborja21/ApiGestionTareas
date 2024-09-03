package com.apis.gestiontareas.apigestiontareas.service;

import com.apis.gestiontareas.apigestiontareas.entity.Tareas;

import java.util.List;

public interface TareasService {

    public List<Tareas> listarTareas();

    public Tareas marcarComoCompletada(Integer idTarea);

    public List<Tareas> findByEstado(String estado);

    public Tareas guardarTarea(Tareas tareas);

    public Tareas actualizarTarea(Tareas tareas);

    public void eliminarTarea(Integer idTarea);

    public List<Tareas> menorFechaVencimiento();

    public List<Tareas> mayorFechaVencimiento();

    public List<Tareas> ordenarPorTitulo();

    public List<Tareas> ordenarPorDescripcion();

}
