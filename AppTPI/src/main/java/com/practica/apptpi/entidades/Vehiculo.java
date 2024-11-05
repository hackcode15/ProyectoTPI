package com.practica.apptpi.entidades;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
@Builder
public class Vehiculo {

    private int id_vehiculo;
    private String marca;
    private String modelo;
    private int anio;
    private double precio;
    private String estado;

    public Vehiculo(String marca, String modelo, int anio, double precio, String estado) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.precio = precio;
        this.estado = estado;
    }
    
}
