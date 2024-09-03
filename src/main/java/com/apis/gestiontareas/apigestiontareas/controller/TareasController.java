package com.apis.gestiontareas.apigestiontareas.controller;

import com.apis.gestiontareas.apigestiontareas.entity.Tareas;
import com.apis.gestiontareas.apigestiontareas.service.TareasService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tareas")
@AllArgsConstructor

public class TareasController {

    private TareasService tareasService;

    @GetMapping("/listarTareas")

    public ResponseEntity<List<Tareas>> listarTareas() {

        return new ResponseEntity<>(tareasService.listarTareas(), HttpStatus.OK);

    }

    @PostMapping("/guardarTarea")

    public ResponseEntity<Tareas> guardarTarea(@RequestBody Tareas tareas) {

        return new ResponseEntity<>(tareasService.guardarTarea(tareas), HttpStatus.OK);

    }

    @PutMapping("/actualizarTarea")

    public ResponseEntity<Tareas> actualizarTarea(@RequestBody Tareas tareas) {

        return new ResponseEntity<>(tareasService.actualizarTarea(tareas), HttpStatus.OK);

    }

    @DeleteMapping("/eliminarTarea/{idTarea}")

    public void eliminarTarea(@PathVariable Integer idTarea) {

        tareasService.eliminarTarea(idTarea);

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

    @GetMapping("/marcarComoCompletada/{idTarea}")

    public ResponseEntity<Tareas> marcarComoCompletada (@PathVariable Integer idTarea) {

        return new ResponseEntity<>(tareasService.marcarComoCompletada(idTarea), HttpStatus.OK);

    }

}
