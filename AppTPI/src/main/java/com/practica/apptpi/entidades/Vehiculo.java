package com.practica.apptpi.entidades;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
public class Vehiculo {

    private int id_vehiculo;
    private String marca;
    private String modelo;
    private int anio;
    private double precio;
    private String estado;
    
}
