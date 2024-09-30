package com.apis.gestiontareas.apigestiontareas.controller;

import com.apis.gestiontareas.apigestiontareas.entity.Estado;
import com.apis.gestiontareas.apigestiontareas.entity.Tareas;
import com.apis.gestiontareas.apigestiontareas.entity.Usuario;
import com.apis.gestiontareas.apigestiontareas.repository.RepositoryAuthUsuario;
import com.apis.gestiontareas.apigestiontareas.service.TareasService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarea")
@AllArgsConstructor

public class TareasController {

    private TareasService tareasService;
    private RepositoryAuthUsuario repositoryAuthUsuario;

    @GetMapping("/listarTareas")

    public ResponseEntity<?> listarTareas(Authentication authentication) {

        String username = authentication.getName();
        Usuario usuario = repositoryAuthUsuario.findByUsername(username).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Tareas> tareas = tareasService.listarTareasPorUsuario(usuario);

       if (tareas.isEmpty()) {

            return ResponseEntity.status(HttpStatus.OK).body("El usuario " + username + " no tiene tareas");

        }

        return new ResponseEntity<>(tareas, HttpStatus.OK);

    }

    @PostMapping("/guardarTarea")

    public ResponseEntity<Tareas> guardarTarea(@RequestBody Tareas tareas) {

        try {

            tareas.setEstado(Estado.PENDIENTE);
            Tareas agregarTarea = tareasService.guardarTarea(tareas);
            return new ResponseEntity<>(agregarTarea, HttpStatus.CREATED);

        } catch (Exception e) {

            throw e;

        }

    }

    @PutMapping("/actualizarTarea")

    public ResponseEntity<Tareas> actualizarTarea(@RequestBody Tareas tareas) {

        Tareas tareaEditada = tareasService.actualizarTarea(tareas);
        return new ResponseEntity<>(tareaEditada, HttpStatus.OK);


    }

    @DeleteMapping("/eliminarTarea/{idTarea}")

    public ResponseEntity<?> eliminarTarea(@PathVariable Integer idTarea) {

        tareasService.eliminarTarea(idTarea);
        return new ResponseEntity<>("Eliminado Correctamente",HttpStatus.OK);

    }

    @GetMapping("/findByEstado/{estado}")

    public ResponseEntity<List<Tareas>> findByEstado (@PathVariable String estado) {

        return new ResponseEntity<>(tareasService.findByEstado(estado), HttpStatus.OK);

    }

    @GetMapping("/menorFechaVencimiento")

    public ResponseEntity<List<Tareas>> menorFechaVencimiento() {

        return new ResponseEntity<>(tareasService.menorFechaVencimiento(), HttpStatus.OK);

    }

    @GetMapping("/mayorFechaVencimiento")

    public ResponseEntity<List<Tareas>> mayorFechaVencimiento() {

        return new ResponseEntity<>(tareasService.mayorFechaVencimiento(), HttpStatus.OK);

    }

    @GetMapping("/ordenarPorTitulo")

    public ResponseEntity<List<Tareas>> ordenarPorTitulo() {

        return new ResponseEntity<>(tareasService.ordenarPorTitulo(), HttpStatus.OK);

    }

    @GetMapping("/ordenarPorDescripcion")

    public ResponseEntity<List<Tareas>> ordenarPorDescripcion() {

        return new ResponseEntity<>(tareasService.ordenarPorDescripcion(), HttpStatus.OK);

    }

}
