package com.practica.apptpi.controladores;

import com.practica.apptpi.dao.AdministradorDAO;
import com.practica.apptpi.entidades.Administrador;
import java.util.*;

public class AdministradorControlador {

    private AdministradorDAO administradorDAO;
    
    public AdministradorControlador(){
        administradorDAO = new AdministradorDAO();
    }
    
    // uso del metodo - read
    public void listarAdministradores(){
        
        List<Administrador> listaAdministradores = administradorDAO.read();
        
        // Formas de imprimir
        // 1.
        /*for (Administrador admin : listaAdministradores) {
            
            System.out.println("Nombre: " + admin.getUsuario().getNombre() + ", Estado: " + admin.getEstado());
            
        }*/
        
        // 2.
        /*System.out.printf("%-16s %-16s%n", "NOMBRE", "ESTADO");
        for (Administrador admin : listaAdministradores) {
            
            System.out.printf("%-16s %-16s", admin.getUsuario().getNombre(), admin.getEstado());
            
        }*/
        
        //3.
        /*System.out.printf("%-16s %-16s %-16s %-16s %-16s%n", "NOMBRE", "APELLIDO", "TELEFONO", "AREA", "ESTADO");
        
        listaAdministradores.stream()
                .map(p -> String.format(
                        "%-16s %-16s %-16s %-16s %-16s",
                        p.getUsuario().getNombre(),
                        p.getUsuario().getApellido(),
                        p.getUsuario().getTelefono(),
                        p.getArea_trabajo(),
                        p.getEstado()))
                .forEach(System.out::println);
        
        System.out.println("");*/
        
    }
    
}
