/*package com.practica.apptpi.dao;

import com.practica.apptpi.crud.OperacionesCrudAdaptadora;
import com.practica.apptpi.conexionBD.ConectorBD;
import com.practica.apptpi.entidades.Vehiculo;
import java.sql.*;
import java.util.*;

public class VehiculoDAO extends OperacionesCrudAdaptadora<Vehiculo> {

    @Override
    public boolean create(Vehiculo entidad) {
        return false;
    }

    @Override
    public List<Vehiculo> read() {
        
        String sql = "SELECT * FROM vehiculo";
        
        try(Connection miConexion = ConectorBD.dameConexion();
                Statement miSentencia = miConexion.createStatement();
                ResultSet rs = miSentencia.executeQuery(sql)){
            
            List<Vehiculo> lista = new ArrayList<>();
            
            while(rs.next()){
                
                Vehiculo vehiculo = new Vehiculo(
                        rs.getInt("id_vehiculo"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("anio"),
                        rs.getDouble("precio"),
                        rs.getString("estado")
                );
                
                lista.add(vehiculo);
                
            }
            
            return lista;
            
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
        
    }

    @Override
    public boolean update(Vehiculo entidad) {
        return false;
    }

    @Override
    public boolean delete(Vehiculo entidad) {
        return false;
    }

    @Override
    public Vehiculo searchById(int id) {
        return null;
    }
    
}*/
