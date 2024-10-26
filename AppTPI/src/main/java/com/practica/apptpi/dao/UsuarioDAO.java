package com.practica.apptpi.dao;

import com.practica.apptpi.entidades.Usuario;
import com.practica.apptpi.conexionBD.ConectorBD;
import java.sql.*;
import java.util.*;

/*
Clases DAO (Data Access Object:
Se encarga ÚNICAMENTE de operaciones de acceso a datos (CRUD)
Solo sabe cómo interactuar con la base de datos
 */
public class UsuarioDAO {

    private ConectorBD con;

    public UsuarioDAO() {
        con = new ConectorBD();
    }

    // Metodo para almacenar los usuarios
    public List<Usuario> listar_usuarios() {

        List<Usuario> lista = new ArrayList<>();

        String sql = "SELECT * FROM usuario";

        // try - catch - resources
        try (Connection miConexion = con.dameConexion(); Statement miSentencia = miConexion.createStatement(); ResultSet rs = miSentencia.executeQuery(sql)) {

            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt("id_usuario"), rs.getString("nombre"), rs.getString("contrasena"), rs.getString("correo"), rs.getBoolean("esAdmin"));
                lista.add(usuario);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;

    }

    public boolean agregar_usuario(Usuario usuario) {

        String sql = "{CALL agregar_usuario(?, ?, ?, ?)}";

        try (Connection miConexion = con.dameConexion(); CallableStatement miSentencia = miConexion.prepareCall(sql)) {

            // Campos obligatorios
            miSentencia.setString(1, usuario.getNombre());
            miSentencia.setString(2, usuario.getContrasena());

            // Campo opcional
            if (usuario.getCorreo() != null && usuario.getCorreo().isEmpty()) {
                miSentencia.setNull(3, java.sql.Types.VARCHAR);
            } else {
                miSentencia.setString(3, usuario.getCorreo());
            }

            // Campo obligatorio
            miSentencia.setBoolean(4, usuario.isEsAdmin());

            int filasAfectadas = miSentencia.executeUpdate();

            return filasAfectadas > 0;

        } catch (Exception e) {
            System.out.println("Error al agregar usuario: " + e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    // Metodo para actualizar
    public boolean actualizar_usuario(Usuario usuario) {

        String sql = "{CALL actualizar_usuario(?, ?, ?)}";

        try (Connection miConexion = con.dameConexion(); CallableStatement miSentencia = miConexion.prepareCall(sql)) {

            miSentencia.setInt(1, usuario.getId_usuario());
            miSentencia.setString(2, usuario.getContrasena());
            miSentencia.setBoolean(3, usuario.isEsAdmin());
            
            int filasAfectadas = miSentencia.executeUpdate();
            
            return filasAfectadas > 0;

        } catch (Exception e) {
            System.out.println("Error al actualizar: " + e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    // Metodo para eliminar
    public boolean eliminar_usuario(Usuario usuario){
        
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        
        try(Connection miConexion = con.dameConexion();
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
    
    // Metodo para buscar por ID
    public Usuario buscar_por_id(int id) {

        String sql = "SELECT * FROM usuario WHERE id_usuario = ?";

        try (Connection miConexion = con.dameConexion(); PreparedStatement miSentencia = miConexion.prepareStatement(sql)) {

            miSentencia.setInt(1, id);

            ResultSet rs = miSentencia.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt("id_usuario"), rs.getString("nombre"), rs.getString("contrasena"), rs.getString("correo"), rs.getBoolean("esAdmin"));
                return usuario;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

}
