package com.practica.apptpi.entidades;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString(callSuper = true)
@SuperBuilder
public class Administrador extends Usuario {

    private int id_administrador;
    private String area_trabajo;
    private String estado;

    public Administrador(String nombre, String apellido, String contrasena, String correo, String telefono, String area_trabajo, String estado) {
        super(nombre, apellido, contrasena, correo, telefono);
        this.area_trabajo = area_trabajo;
        this.estado = estado;
    }
    
}
