package com.apis.gestiontareas.apigestiontareas.serviceImpl;

import com.apis.gestiontareas.apigestiontareas.entity.Tareas;
import com.apis.gestiontareas.apigestiontareas.repository.RepositoryTareas;
import com.apis.gestiontareas.apigestiontareas.service.TareasService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class TareasServiceImpl implements TareasService {

    private RepositoryTareas repositoryTareas;

    @Override

    public List<Tareas> listarTareas() {

        return repositoryTareas.findAll();

    }

    @Override

    public Tareas marcarComoCompletada(Integer idTarea) {

        Tareas tarea = repositoryTareas.findById(idTarea).get();

        tarea.setEstado("Completada");

        return repositoryTareas.save(tarea);

    }

    @Override

    public List<Tareas> findByEstado(String estado) {

        return repositoryTareas.findByEstado(estado);

    }

    @Override

    public Tareas guardarTarea(Tareas tareas) {

        return repositoryTareas.save(tareas);

    }

    @Override

    public Tareas actualizarTarea(Tareas tareas) {

        var tareaA = repositoryTareas.findById(tareas.getIdTarea()).get();

        tareaA.setTitulo(tareas.getTitulo());
        tareaA.setDescripcion(tareas.getDescripcion());
        tareaA.setFechaVencimiento(tareas.getFechaVencimiento());
        tareaA.setEstado(tareas.getEstado());

        return repositoryTareas.save(tareaA);

    }

    @Override

    public void eliminarTarea(Integer idTarea) {

        repositoryTareas.deleteById(idTarea);

    }

    @Override

    public List<Tareas> menorFechaVencimiento() {

        return repositoryTareas.menorFechaVencimiento();

    }

    @Override

    public List<Tareas> mayorFechaVencimiento() {

        return repositoryTareas.mayorFechaVencimiento();

    }

    @Override

    public List<Tareas> ordenarPorTitulo() {

        return repositoryTareas.ordenarPorTitulo();

    }

    @Override

    public List<Tareas> ordenarPorDescripcion() {

        return repositoryTareas.ordenarPorDescripcion();

    }
}
