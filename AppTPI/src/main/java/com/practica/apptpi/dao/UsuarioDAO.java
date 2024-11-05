// NO HARIA FALTA


/*package com.practica.apptpi.dao;

import com.practica.apptpi.entidades.Usuario;
import com.practica.apptpi.conexionBD.ConectorBD;
import java.sql.*;
import java.util.*;

public class UsuarioDAO extends OperacionesCRUD<Usuario> {

    @Override
    public boolean create(Usuario usuario) {
    
        String sql = "{CALL agregar_usuario(?, ?, ?, ?, ?)}";
        
        try(Connection miConexion = ConectorBD.dameConexion();
                CallableStatement miSentencia = miConexion.prepareCall(sql)){
            
            miSentencia.setString(1, usuario.getNombre());
            
            if(usuario.getApellido() != null && usuario.getApellido().isEmpty()){
                miSentencia.setNull(2, java.sql.Types.VARCHAR);
            } else {
                miSentencia.setString(2, usuario.getApellido());
            }
            
            miSentencia.setString(3, usuario.getContrasena());
            miSentencia.setString(4, usuario.getCorreo());
            
            if(usuario.getTelefono() != null && usuario.getTelefono().isEmpty()){
                miSentencia.setNull(5, java.sql.Types.VARCHAR);
            } else {
                miSentencia.setString(5, usuario.getTelefono());
            }

            int filasAfectadas = miSentencia.executeUpdate();

            return filasAfectadas > 0;
            
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        
    }

    @Override
    public List<Usuario> read() {
        
        List<Usuario> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM usuario";

        // try - catch - resources
        try (Connection miConexion = ConectorBD.dameConexion(); 
                Statement miSentencia = miConexion.createStatement(); 
                ResultSet rs = miSentencia.executeQuery(sql)) {

            while (rs.next()) {
                
                Usuario usuario = new Usuario(
                        rs.getInt("id_usuario"), 
                        rs.getString("nombre"), 
                        rs.getString("apellido"), 
                        rs.getString("contrasena"), 
                        rs.getString("correo"), 
                        rs.getString("telefono"));
                
                lista.add(usuario);
            
            }
            
            return lista;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean update(Usuario usuario) {
        
        String sql = "{CALL actualizar_usuario(?, ?, ?)}";

        try (Connection miConexion = ConectorBD.dameConexion(); 
                CallableStatement miSentencia = miConexion.prepareCall(sql)) {

            miSentencia.setInt(1, usuario.getId_usuario());
            miSentencia.setString(2, usuario.getContrasena());
            miSentencia.setString(3, usuario.getTelefono());
            
            int filasAfectadas = miSentencia.executeUpdate();
            
            return filasAfectadas > 0;

        } catch (Exception e) {
            System.out.println("Error al actualizar: " + e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean delete(Usuario usuario) {
        
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        
        try(Connection miConexion = ConectorBD.dameConexion();
                PreparedStatement miSentencia = miConexion.prepareStatement(sql)){
            
            miSentencia.setInt(1, usuario.getId_usuario());
            
            int filasAfectadas = miSentencia.executeUpdate();
            
            return filasAfectadas > 0;
            
        }catch(Exception e){
            System.out.println("Error al eliminar: " + e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public Usuario searchById(int id) {
        
        String sql = "SELECT * FROM usuario WHERE id_usuario = ?";

        try (Connection miConexion = ConectorBD.dameConexion(); 
                PreparedStatement miSentencia = miConexion.prepareStatement(sql)) {

            miSentencia.setInt(1, id);

            try(ResultSet rs = miSentencia.executeQuery()){
                
                if (rs.next()) {
                    
                    Usuario usuario = new Usuario(
                            rs.getInt("id_usuario"), 
                            rs.getString("nombre"),
                            rs.getString("apellido"), 
                            rs.getString("contrasena"), 
                            rs.getString("correo"), 
                            rs.getString("telefono")
                    );
                    
                    return usuario;
                
                }else{
                    return null;
                }
            
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }

    }

}*/
