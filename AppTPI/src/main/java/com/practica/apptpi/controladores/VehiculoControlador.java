package com.practica.apptpi.controladores;

import com.practica.apptpi.dao.VehiculoDAO;
import com.practica.apptpi.entidades.Vehiculo;
import java.util.*;

public class VehiculoControlador {

    private VehiculoDAO vehiculoDAO;
    
    public VehiculoControlador(){
        vehiculoDAO = new VehiculoDAO();
    }
    
    // uso del metodo - read
    public void listarVehiculos(){
        
        List<Vehiculo> listaVehiculos = vehiculoDAO.read();
        
        System.out.printf(
                "%-16s %-16s %-16s %-16s %-16s %-16s%n",
                "ID_VEHICULO",
                "MARCA",
                "MODELO",
                "AÑO",
                "PRECIO",
                "ESTADO");
        
        listaVehiculos.stream()
                .map(p -> String.format(
                        "%-16s %-16s %-16s %-16s %-16s %-16s",
                        p.getId_vehiculo(),
                        p.getMarca(),
                        p.getModelo(),
                        p.getAnio(),
                        p.getPrecio(),
                        p.getEstado()))
                .forEach(System.out::println);
        
        System.out.println("");
        
    }
    
}
