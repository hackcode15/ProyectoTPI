package com.practica.apptpi.entidades;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor // Generacion de constructor con todos los parametros
@AllArgsConstructor // Generacion de constructor sin parametros
@Getter // Generacion de metodos Getters
@Setter // Generacion de metodos Setters
@ToString(callSuper = true) // Generacion del metodo ToString (con los atributos de la clase padre)
@SuperBuilder // Simplifica la creacion de objetos (soportar herencia)
public class Cliente extends Usuario{

    // Atributos
    private int id_cliente;
    private String domicilio;
    private String tipoRegimenLaboral;

    // Constructor personalizado
    public Cliente(String nombre, String apellido, String contrasena, String correo, String telefono, String domicilio, String tipoRegimenLaboral) {
        super(nombre, apellido, contrasena, correo, telefono);
        this.domicilio = domicilio;
        this.tipoRegimenLaboral = tipoRegimenLaboral;
    }
    
}
