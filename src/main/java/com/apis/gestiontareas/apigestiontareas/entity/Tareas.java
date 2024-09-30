package com.apis.gestiontareas.apigestiontareas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Entity
@Data

public class Tareas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTarea;

    @NotBlank (message = "El titulo es obligatorio")
    private String titulo;

    @NotBlank (message = "La descripcion es obligatoria")
    private String descripcion;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date fechaVencimiento;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = true)
    @JsonBackReference
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private Estado estado = Estado.PENDIENTE;

}
