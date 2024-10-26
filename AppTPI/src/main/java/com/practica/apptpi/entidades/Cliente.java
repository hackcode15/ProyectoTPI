package com.practica.apptpi.entidades;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
public class Cliente {

    private int id_cliente; // PK
    private String nombre;
    private String dni;
    private String telefono;
    private String domicilio;
    private int id_usuario; // FK
    
}
