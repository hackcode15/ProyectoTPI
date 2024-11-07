package com.practica.apptpi.controladores;

import com.practica.apptpi.dao.VehiculoDAO;
import com.practica.apptpi.entidades.Vehiculo;
import java.util.*;

public class VehiculoControlador { 

    // instaciar el DAO de vehiculo
    private VehiculoDAO vehiculoDAO;
    
    public VehiculoControlador(){
        this.vehiculoDAO = new VehiculoDAO();
    }
    
    // metodos crud
    public void listarVehiculos(){
        
        List<Vehiculo> listaVehiculos = vehiculoDAO.read();
        
        // forma de basico de imprimir
        for (int i = 0; i < listaVehiculos.size(); i++) {
            System.out.println(listaVehiculos.get(i));
        }
        
        for (Vehiculo vehiculo : listaVehiculos) {
            System.out.println(vehiculo);
        }
        
    }
    
}
