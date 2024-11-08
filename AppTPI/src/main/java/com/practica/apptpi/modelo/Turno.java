package com.practica.apptpi.modelo;

import java.util.Date;
import lombok.*;

@Getter @Setter @ToString
@Builder
public class Turno {
    
    private int id_turno;
    private Date fecha;
    private Vehiculo vehiculo;
    private Cliente cliente;
    
}
