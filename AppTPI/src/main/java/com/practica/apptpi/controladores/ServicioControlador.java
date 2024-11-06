/*package com.practica.apptpi.controladores;

import com.practica.apptpi.dao.ServicioDAO;
import com.practica.apptpi.entidades.Servicio;
import java.util.*;

public class ServicioControlador {

    private ServicioDAO servicioDAO;
    
    public ServicioControlador(){
        servicioDAO = new ServicioDAO();
    }
    
    // usu del metodo - read
    public void listarServicios(){
        
        List<Servicio> listaServicios = servicioDAO.read();
        
        System.out.printf(
                "%-16s %-30s %-16s %-16s%n",
                "ID_SERVICIO",
                "NOMBRE",
                "COSTO",
                "ESTADO"
                );
        
        listaServicios.stream()
                .map(p -> String.format(
                        "%-16s %-30s %-16s %-16s", 
                        p.getId_servicio(),
                        p.getNombre(),
                        p.getCosto(),
                        p.getEstado()))
                .forEach(System.out::println);
        
        System.out.println("");
        
    }
    
}*/
