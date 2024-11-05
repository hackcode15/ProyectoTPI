package com.practica.apptpi.entidades;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
//@Builder
@SuperBuilder // Cambiamos @Builder por @SuperBuilder para soportar herencia
public class Usuario {

    private int id_usuario;
    private String nombre;
    private String apellido;
    private String contrasena;
    private String correo;
    private String telefono;

    public Usuario(String nombre, String apellido, String contrasena, String correo, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
        this.correo = correo;
        this.telefono = telefono;
    }
    
}
