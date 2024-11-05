package com.practica.apptpi.entidades;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
@Builder
public class Servicio {

    private int id_servicio;
    private String nombre;
    private double costo;
    private String estado;

    public Servicio(String nombre, double costo, String estado) {
        this.nombre = nombre;
        this.costo = costo;
        this.estado = estado;
    }

}
