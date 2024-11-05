package com.practica.apptpi.entidades;

import lombok.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
@Builder
public class Turno {
    
    private int id_turno;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private Servicio servicio;
    private Date fecha;
    private double costo_total;
    private boolean estado;

    public Turno(Cliente cliente, Vehiculo vehiculo, Servicio servicio, Date fecha, double costo_total, boolean estado) {
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.servicio = servicio;
        this.fecha = fecha;
        this.costo_total = costo_total;
        this.estado = estado;
    }
    
}
