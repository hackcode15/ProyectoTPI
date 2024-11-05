package com.practica.apptpi.dao;

import com.practica.apptpi.conexionBD.ConectorBD;
import com.practica.apptpi.entidades.Administrador;
import com.practica.apptpi.entidades.Usuario;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdministradorDAO extends OperacionesCRUD<Administrador> {

    @Override
    public boolean create(Administrador administrador) {
        
        String sqlUsuario = "INSERT INTO usuario(nombre, apellido, contrasena, correo, telefono) VALUES(?, ?, ?, ?, ?)";
        String sqlAdministrador = "{CALL agregar_administrador(?, ?, ?)}"; // (area_trabajo, estado, id_usuario)
        
        Connection miConexion = null;
        PreparedStatement miSentenciaUsuario = null;
        CallableStatement miSentenciaAdministrador = null;
        
        try{
            
            miConexion = ConectorBD.dameConexion();
            miConexion.setAutoCommit(false);
            
            miSentenciaUsuario = miConexion.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);
            
            miSentenciaUsuario.setString(1, administrador.getNombre());
            miSentenciaUsuario.setString(2, administrador.getApellido());
            miSentenciaUsuario.setString(3, administrador.getContrasena());
            miSentenciaUsuario.setString(4, administrador.getCorreo());
            miSentenciaUsuario.setString(5, administrador.getTelefono());
            
            int filasAfectadasEnUsuario = miSentenciaUsuario.executeUpdate();
            
            if(filasAfectadasEnUsuario == 0){
                throw new SQLException("Error en la agregacion del usuario, ninguna fila afectada");
            }
            
            try(ResultSet idGenerado = miSentenciaUsuario.getGeneratedKeys()){
                
                if(idGenerado.next()){
                    administrador.setId_usuario(idGenerado.getInt(1));
                }else{
                    throw new SQLException("Error en la obtencion del id del usuario");
                }
                
            }
            
            miSentenciaAdministrador = miConexion.prepareCall(sqlAdministrador);
            
            miSentenciaAdministrador.setString(1, administrador.getArea_trabajo());
            
            // En este caso, en omitir, se asignara por defecto 'ACTIVO'
            miSentenciaAdministrador.setString(2, administrador.getEstado());
            miSentenciaAdministrador.setInt(3, administrador.getId_usuario());
            
            int filasAfectadasEnAdministrador = miSentenciaAdministrador.executeUpdate();
            
            if(filasAfectadasEnAdministrador == 0){
                throw new SQLException("Error en la agregacion del administrador, ninguna fila afectada");
            }
            
            miConexion.commit();
            return true;
            
        }catch(SQLException e){
            
            try {
                miConexion.rollback();
            } catch (SQLException ex) {
                System.out.println("Error en revertir la operacion: " + ex.getMessage());
            }
            
            System.out.println("Error al agregar un administrador: " + e.getMessage());
            return false;
            
        }finally{
            
            try{
                
                if(miSentenciaUsuario != null && miSentenciaAdministrador != null && miConexion != null){
                    
                    miSentenciaUsuario.close();
                    miSentenciaAdministrador.close();
                    
                    miConexion.setAutoCommit(true);
                    miConexion.close();
                    
                }
                
            }catch(SQLException e){
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }
            
        }
        
    }

    @Override
    public List<Administrador> read() {
            
        String sql = "{CALL listar_administrador}"; //(nombre, apellido, correo, telefono, area_trabajo, estado)
        
        try(Connection miConexion = ConectorBD.dameConexion();
                CallableStatement miSentencia = miConexion.prepareCall(sql);
                ResultSet rs = miSentencia.executeQuery()){
            
            List<Administrador> lista = new ArrayList<>();
            
            while(rs.next()){
                
                Administrador administrador = Administrador.builder()
                        .nombre(rs.getString("nombre"))
                        .apellido(rs.getString("apellido"))
                        .correo(rs.getString("correo"))
                        .telefono(rs.getString("telefono"))
                        .area_trabajo(rs.getString("area_trabajo"))
                        .estado(rs.getString("estado"))
                        .build();
                
                lista.add(administrador);
                
            }
            
            return lista;
            
        }catch(SQLException e){
            System.out.println("Error al listar los administradores: " + e.getMessage());
            return null;
        }
        
    }

    @Override
    public boolean update(Administrador administrador) {
        
        return false;
        
    }

    @Override
    public boolean delete(Administrador administrador) {
        return false;
    }

    @Override
    public Administrador searchById(int id) {
        return null;
    }
    
}
