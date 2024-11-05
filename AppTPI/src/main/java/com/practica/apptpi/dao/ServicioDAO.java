package com.practica.apptpi.dao;

import com.practica.apptpi.conexionBD.ConectorBD;
import com.practica.apptpi.entidades.Servicio;
import java.sql.*;
import java.util.*;

public class ServicioDAO extends OperacionesCRUD<Servicio> {

    @Override
    public boolean create(Servicio entidad) {
        return false;
    }

    @Override
    public List<Servicio> read() {
        
        String sql = "SELECT * FROM servicio";
        
        try(Connection miConexion = ConectorBD.dameConexion();
                Statement miSentencia = miConexion.createStatement();
                ResultSet rs = miSentencia.executeQuery(sql)){
            
            List<Servicio> lista = new ArrayList<>();
            
            while(rs.next()){
                
                Servicio servicio = new Servicio(
                        rs.getInt("id_servicio"),
                        rs.getString("nombre"),
                        rs.getDouble("costo"),
                        rs.getString("estado")
                );
                
                lista.add(servicio);
                
            }
            
            return lista;
            
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
        
    }

    @Override
    public boolean update(Servicio entidad) {
        return false;
    }

    @Override
    public boolean delete(Servicio entidad) {
        return false;
    }

    @Override
    public Servicio searchById(int id) {
        return null;
    }

}
