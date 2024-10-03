package com.apis.gestiontareas.apigestiontareas.serviceImpl;

import com.apis.gestiontareas.apigestiontareas.entity.Estado;
import com.apis.gestiontareas.apigestiontareas.entity.Tareas;
import com.apis.gestiontareas.apigestiontareas.entity.Usuario;
import com.apis.gestiontareas.apigestiontareas.exception.EntityNotFoundException;
import com.apis.gestiontareas.apigestiontareas.repository.RepositoryAuthUsuario;
import com.apis.gestiontareas.apigestiontareas.repository.RepositoryTareas;
import com.apis.gestiontareas.apigestiontareas.service.TareasService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class TareasServiceImpl implements TareasService {

    private RepositoryTareas repositoryTareas;
    private RepositoryAuthUsuario usuarioRepository;

    @Override

    public List<Tareas> listarTareas() {

        return repositoryTareas.findAll();

    }


    @Override

    public List<Tareas> findByEstado(String estado) {

        Estado estados = Estado.valueOf(estado.toUpperCase());

        return repositoryTareas.findByEstado(estados);

    }

    @Override
    public Tareas guardarTarea(Tareas tareas) {

        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        tareas.setUsuario(usuario);
        return repositoryTareas.save(tareas);

    }

    @Override

    public Tareas actualizarTarea(Tareas tareas) {

        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        var tareaA = repositoryTareas.findById(tareas.getIdTarea()).orElseThrow(
                () -> new EntityNotFoundException("Tarea no encontrada")
        );

        if (!tareaA.getUsuario().equals(usuario)) {
            throw new EntityNotFoundException("Esta tarea pertenece al usuario " + tareaA.getUsuario().getUsername());
        }
        if (tareaA.getEstado() == Estado.COMPLETADA) {
            throw new EntityNotFoundException("No se puede editar una tarea completada");
        }

        tareaA.setTitulo(tareas.getTitulo());
        tareaA.setDescripcion(tareas.getDescripcion());
        tareaA.setFechaVencimiento(tareas.getFechaVencimiento());
        tareaA.setEstado(tareas.getEstado());

        return repositoryTareas.save(tareaA);

    }

    @Override

    public void eliminarTarea(Integer idTarea) {

        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        Tareas tarea = repositoryTareas.findById(idTarea)
                .orElseThrow(() -> new EntityNotFoundException("Tarea no encontrada"));

        if (!tarea.getUsuario().equals(usuario)) {
            throw new EntityNotFoundException("Esta tarea pertenece al usuario " + tarea.getUsuario().getUsername());
        }
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

    @Override
    public List<Tareas> listarTareasPorUsuario(Usuario usuario) {

        return repositoryTareas.findByUsuario(usuario);

    }
}

