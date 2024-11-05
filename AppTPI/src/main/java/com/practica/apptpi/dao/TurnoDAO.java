package com.practica.apptpi.dao;

/*import com.practica.apptpi.conexionBD.ConectorBD;
import com.practica.apptpi.entidades.Turno;
import com.practica.apptpi.entidades.*;
import java.sql.*;
import java.util.*;

public class TurnoDAO extends OperacionesCRUD<Turno>{

    @Override
    public boolean create(Turno turno) {
        return false;
    }*/

    /*@Override
    public List<Turno> read() {
        
        String sql = "{CALL listar_turno}";
        
        try(Connection miConexion = ConectorBD.dameConexion();
                CallableStatement miSentencia = miConexion.prepareCall(sql);
                ResultSet rs = miSentencia.executeQuery()){
            
            List<Turno> lista = new ArrayList<>();
            
            while(rs.next()){
                
                Usuario usuario = Usuario.builder()
                .nombre(rs.getString("nombre"))
                .apellido(rs.getString("apellido"))
                .build();
            
                Cliente cliente = Cliente.builder()
                    .id_cliente(rs.getInt("id_cliente"))
                    .usuario(usuario)
                    .build();
            
                Vehiculo vehiculo = Vehiculo.builder()
                    .id_vehiculo(rs.getInt("id_vehiculo"))
                    .marca(rs.getString("marca"))
                    .modelo(rs.getString("modelo"))
                    .build();

                Servicio servicio = Servicio.builder()
                    .id_servicio(rs.getInt("id_servicio"))
                    .nombre(rs.getString("nombre_servicio"))
                    .build();

                Turno turno = Turno.builder()
                    .id_turno(rs.getInt("id_turno"))
                    .cliente(cliente)
                    .vehiculo(vehiculo)
                    .servicio(servicio)
                    .fecha(rs.getDate("fecha"))
                    .costo_total(rs.getDouble("costo_total"))
                    .estado(rs.getBoolean("estado"))
                    .build();
                
                lista.add(turno);
                
            }
            
            return lista;
            
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
        
    }*/
    
    /*@Override
    public List<Turno> read() {
        
        String sql = "{CALL listar_turno}";
        
        try(Connection miConexion = ConectorBD.dameConexion();
                CallableStatement miSentencia = miConexion.prepareCall(sql);
                ResultSet rs = miSentencia.executeQuery()){
            
            List<Turno> lista = new ArrayList<>();
            
            while(rs.next()){
                
                Usuario usuario = Usuario.builder()
                        .nombre(rs.getString("nombre_usuario"))
                        .build();
                
                Cliente cliente = Cliente.builder()
                        .id_cliente(rs.getInt("id_cliente"))
                        .usuario(usuario)
                        .build();
                
                Vehiculo vehiculo = Vehiculo.builder()
                        .marca(rs.getString("marca"))
                        .modelo(rs.getString("modelo"))
                        .build();
                
                Servicio servicio = Servicio.builder()
                        .nombre(rs.getString("nombre_servicio"))
                        .build();
                
                Turno turno = Turno.builder()
                        .cliente(cliente)
                        .vehiculo(vehiculo)
                        .servicio(servicio)
                        .fecha(rs.getDate("fecha"))
                        .costo_total(rs.getDouble("costo_total"))
                        .estado(rs.getBoolean("estado"))
                        .build();
                
                lista.add(turno);
                
            }
            
            return lista;
            
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
        
    }

    @Override
    public boolean update(Turno turno) {
        return false;
    }

    @Override
    public boolean delete(Turno turno) {
       return false; 
    }

    @Override
    public Turno searchById(int id) {
        return null;
    }
    
    // Metodos propios personalizados
    public List<Turno> turnoDeCliente(Cliente clienteParametro){
        
        String sql = "{CALL mostrar_turno_de_cliente(?)}";
        
        try(Connection miConexion = ConectorBD.dameConexion();
                CallableStatement miSentencia = miConexion.prepareCall(sql)){
            
            List<Turno> lista = new ArrayList<>();
            
            miSentencia.setInt(1, clienteParametro.getId_cliente());
            
            try(ResultSet rs = miSentencia.executeQuery()){
                
                while(rs.next()){
                    
                    Usuario usuario = Usuario.builder()
                            .nombre(rs.getString("NOMBRE"))
                            .build();
                    
                    Cliente cliente = Cliente.builder()
                            .id_cliente(rs.getInt("ID_CLIENTE"))
                            .usuario(usuario)
                            .build();
                    
                    Vehiculo vehiculo = Vehiculo.builder()
                            .marca(rs.getString("MARCA"))
                            .modelo(rs.getString("MODELO"))
                            .build();
                    
                    Servicio servicio = Servicio.builder()
                            .nombre(rs.getString("SERVICIO_SOLICITADO"))
                            .costo(rs.getDouble("PRECIO"))
                            .build();
                    
                    Turno turno = Turno.builder()
                            .cliente(cliente)
                            .vehiculo(vehiculo)
                            .servicio(servicio)
                            .fecha(rs.getDate("FECHA_TURNO"))
                            .build();
                    
                    lista.add(turno);
                    
                }
                
                return lista;
                
            }
            
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
        
        
    }
  
}
*/