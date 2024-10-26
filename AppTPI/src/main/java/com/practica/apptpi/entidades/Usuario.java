package com.practica.apptpi.entidades;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
public class Usuario {

    private int id_usuario;
    private String nombre;
    private String contrasena;
    private String correo;
    private boolean esAdmin;

    public Usuario(String nombre, String contrasena, String correo, boolean esAdmin) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correo = correo;
        this.esAdmin = esAdmin;
    }
    
}
