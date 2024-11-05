package com.practica.apptpi.dao;

import com.practica.apptpi.conexionBD.ConectorBD;
import com.practica.apptpi.entidades.Cliente;
import java.sql.*;
import java.util.*;

public class ClienteDAO extends OperacionesCRUD<Cliente> {

    @Override
    public boolean create(Cliente cliente){
        
        // No se puede recuperar el ID usando un procedimiento almacenado
        //String sqlUsuario = "{CALL agregar_usuario(?, ?, ?, ?, ?)}"; // (nombre, apellido, contrasena, correo, telefono)
        
        // Lo hacemos con una consulta preparada
        String sqlUsuario = "INSERT INTO usuario(nombre, apellido, contrasena, correo, telefono) VALUES(?, ?, ?, ?, ?)";
        
        String sqlCliente = "{CALL agregar_cliente(?, ?, ?)}"; // (domicilio, tipoRegimenLaboral, id_usuario)
        
        Connection miConexion = null;
        //CallableStatement miSentenciaUsuario = null;
        PreparedStatement miSentenciaUsuario = null;
        CallableStatement miSentenciaCliente= null;
        
        try{
            
            miConexion = ConectorBD.dameConexion();
            miConexion.setAutoCommit(false);
            
            //miSentenciaUsuario = miConexion.prepareCall(sqlUsuario, Statement.RETURN_GENERATED_KEYS);
            
            miSentenciaUsuario = miConexion.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);
            
            miSentenciaUsuario.setString(1, cliente.getNombre());
            miSentenciaUsuario.setString(2, cliente.getApellido());
            miSentenciaUsuario.setString(3, cliente.getContrasena());
            miSentenciaUsuario.setString(4, cliente.getCorreo());
            miSentenciaUsuario.setString(5, cliente.getTelefono());
            
            int filasAfectadasEnUsuario = miSentenciaUsuario.executeUpdate();
            
            if(filasAfectadasEnUsuario == 0){
                throw new SQLException("Error: La creacion del usuario fallo, ninguna fila afectada");
            }
            
            try(ResultSet idGenerado = miSentenciaUsuario.getGeneratedKeys()){
                
                if(idGenerado.next()){
                    cliente.setId_usuario(idGenerado.getInt(1));
                }else{
                    throw new SQLException("Error: La creacion del usuario fallo, no se obtuvo el ID");
                }
                
            }
            
            miSentenciaCliente = miConexion.prepareCall(sqlCliente);
            
            miSentenciaCliente.setString(1, cliente.getDomicilio());
            miSentenciaCliente.setString(2, cliente.getTipoRegimenLaboral());
            miSentenciaCliente.setInt(3, cliente.getId_usuario());
            
            int filasAfectadasEnCliente = miSentenciaCliente.executeUpdate();
            
            if(filasAfectadasEnCliente == 0){
                throw new SQLException("Error: La creacion del cliente fallo, ninguna fila afectada");
            }
            
            miConexion.commit();
            return true;
            
        } catch(SQLException e){
            
            // En caso de error
            try{
                
                if(miConexion != null){
                    miConexion.rollback(); // Revertimos la operacion
                }
                
            }catch(SQLException ex){
                System.out.println("Error al revertir la operacion: " + ex.getMessage());
            }
            
            System.out.println("Error en la creacion del cliente: " + e.getMessage());
            return false;
            
        } finally {
            
            try{
                // cierre de recursos
                if(miSentenciaUsuario != null && miSentenciaCliente != null && miConexion != null){
                    
                    miSentenciaUsuario.close();
                    miSentenciaCliente.close();
                    
                    //miConexion.setAutoCommit(true); // Opcional
                    miConexion.close();
                    
                }
                
            }catch(SQLException e){
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
            
        }
        
    }

    @Override
    public List<Cliente> read() {

        String sql = "{CALL listar_clientes}";
        
        List<Cliente> lista = new ArrayList<>();
        
        try(Connection miConexion = ConectorBD.dameConexion();
                CallableStatement miSentencia = miConexion.prepareCall(sql)){
            
            try(ResultSet rs = miSentencia.executeQuery()){
                
                while(rs.next()){
                    
                    Cliente cliente = Cliente.builder()
                            .nombre(rs.getString("nombre"))
                            .apellido(rs.getString("apellido"))
                            .correo(rs.getString("correo"))
                            .telefono(rs.getString("telefono"))
                            .domicilio(rs.getString("domicilio"))
                            .tipoRegimenLaboral(rs.getString("tipoRegimenLaboral"))
                            .build();
                    
                    lista.add(cliente);
                    
                }
                
                return lista;
                
            }
            
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(Cliente cliente) {
        
        // Actualizar la contraseña y el telefono por ejemplo
        String sql = "{CALL actualizar_usuario_cliente(?, ?, ?)}"; // (id_usuario, contrasena, telefono)
        
        try(Connection miConexion = ConectorBD.dameConexion();
                CallableStatement miSentencia = miConexion.prepareCall(sql)){
            
            miSentencia.setInt(1, cliente.getId_usuario());
            miSentencia.setString(2, cliente.getContrasena());
            miSentencia.setString(3, cliente.getTelefono());
            
            int filasAfectadas = miSentencia.executeUpdate();
        
            return filasAfectadas > 0;
            
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        
    }

    @Override
    public boolean delete(Cliente cliente) {
        
        String slq = "{CALL eliminar_cliente(?)}";
        
        try(Connection miConexion = ConectorBD.dameConexion();
                CallableStatement miSentencia = miConexion.prepareCall(slq)){
            
            miSentencia.setInt(1, cliente.getId_usuario());
            
            int filasAfectadas = miSentencia.executeUpdate();
            
            return filasAfectadas > 0;
            
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        
    }

    @Override
    public Cliente searchById(int id_cliente) {

        String sql = "{CALL buscar_cliente(?)}";
        
        try(Connection miConexion = ConectorBD.dameConexion();
                PreparedStatement miSentencia = miConexion.prepareStatement(sql)){
            
            miSentencia.setInt(1, id_cliente);
            
            try(ResultSet rs = miSentencia.executeQuery()){
                
                if(rs.next()){
                    
                    Cliente cliente = Cliente.builder()
                            .id_usuario(rs.getInt("id_usuario"))
                            .id_cliente(rs.getInt("id_cliente"))
                            .nombre(rs.getString("nombre"))
                            .correo(rs.getString("correo"))
                            .domicilio(rs.getString("domicilio"))
                            .tipoRegimenLaboral(rs.getString("tipoRegimenLaboral"))
                            .build();
                    
                    return cliente;
                    
                }else{
                    return null;
                }
                
            }
            
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }

    }

}
