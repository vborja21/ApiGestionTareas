package com.apis.gestiontareas.apigestiontareas.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data

public class Tareas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer idTarea;
    private String titulo;
    private String descripcion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date fechaVencimiento;
    private String estado = "Pendiente";

}
