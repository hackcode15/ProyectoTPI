package com.practica.apptpi.entidades;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
public class Administrador {

    private int id_administrador; // PK
    private String nombre;
    private String apellido;
    private String correo;
    private int id_usuario; // FK
    
}
