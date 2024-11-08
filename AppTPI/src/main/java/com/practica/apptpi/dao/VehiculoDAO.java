package com.practica.apptpi.dao;

import com.practica.apptpi.conexionBD.ConectorBD;
import com.practica.apptpi.crud.OperacionesCrud;
import com.practica.apptpi.modelo.Vehiculo;
import java.sql.*;
import java.util.*;

public class VehiculoDAO implements OperacionesCrud<Vehiculo> {

    @Override
    public boolean create(Vehiculo vehiculo) {

        String sql = "{CALL agregar_vehiculo(?, ?, ?, ?)}";

        try (Connection miConexion = ConectorBD.dameConexion(); CallableStatement miSentencia = miConexion.prepareCall(sql)) {

            miSentencia.setString(1, vehiculo.getMarca());
            miSentencia.setString(2, vehiculo.getModelo());
            miSentencia.setInt(3, vehiculo.getAnio());
            miSentencia.setInt(4, vehiculo.getDni_cliente());

            int filasAfectadas = miSentencia.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }

    }

    @Override
    public List<Vehiculo> read() {

        String sql = "SELECT * FROM vehiculo";

        try (Connection miConexion = ConectorBD.dameConexion(); Statement miSentencia = miConexion.createStatement(); ResultSet rs = miSentencia.executeQuery(sql)) {

            List<Vehiculo> lista = new ArrayList<>();

            while (rs.next()) {

                Vehiculo vehiculo = Vehiculo.builder()
                        .id_vehiculo(rs.getInt("id_vehiculo"))
                        .marca(rs.getString("marca"))
                        .modelo(rs.getString("modelo"))
                        .anio(rs.getInt("anio"))
                        .build();

                lista.add(vehiculo);

            }

            return lista;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }

    }

    @Override
    public boolean update(Vehiculo vehiculo) {

        String sql = "{CALL actualizar_vehiculo(?, ?, ?, ?, ?)}";

        try (Connection miConexion = ConectorBD.dameConexion(); CallableStatement miSentencia = miConexion.prepareCall(sql)) {

            miSentencia.setInt(1, vehiculo.getId_vehiculo());
            miSentencia.setString(2, vehiculo.getMarca());
            miSentencia.setString(3, vehiculo.getModelo());
            miSentencia.setInt(4, vehiculo.getAnio());
            miSentencia.setInt(5, vehiculo.getDni_cliente());

            int filasAfectadas = miSentencia.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }

    }

    @Override
    public boolean delete(Vehiculo vehiculo) {

        String sql = "DELETE FROM vehiculo WHERE id_vehiculo = ?";

        try (Connection miConexion = ConectorBD.dameConexion(); PreparedStatement miSentencia = miConexion.prepareStatement(sql)) {

            miSentencia.setInt(1, vehiculo.getId_vehiculo());

            int filasAfectadas = miSentencia.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }

    }

    @Override
    public Vehiculo searchByDni(int id_vehiculo) {

        String sql = "{CALL buscar_vehiculo(?)}";

        try (Connection miConexion = ConectorBD.dameConexion(); CallableStatement miSentencia = miConexion.prepareCall(sql)) {

            miSentencia.setInt(1, id_vehiculo);

            try (ResultSet rs = miSentencia.executeQuery()) {

                if (rs.next()) {

                    Vehiculo vehiculo = Vehiculo.builder()
                            .id_vehiculo(rs.getInt("id_vehiculo"))
                            .marca(rs.getString("marca"))
                            .modelo(rs.getString("modelo"))
                            .anio(rs.getInt("anio"))
                            .dni_cliente(rs.getInt("dni_cliente"))
                            .build();

                    return vehiculo;

                } else {
                    return null;
                }

            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }

    }

    // Metodo propio especifico
    // Verificar que el vehiculo para actualizar sea de ese cliente
    public boolean verificarTuVehiculo(int id_vehiculo, int dni_cliente) {

        String sql = "{CALL verificar_vehiculo_a_modificar(?, ?)}";

        try (Connection miConexion = ConectorBD.dameConexion(); CallableStatement miSentencia = miConexion.prepareCall(sql)) {

            miSentencia.setInt(1, id_vehiculo);
            miSentencia.setInt(2, dni_cliente);

            try (ResultSet rs = miSentencia.executeQuery()) {

                return rs.next();

            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }

    }

    // Metodo propio especifico
    public List<Vehiculo> listarTusVehiculosRegistrados(int dni_cliente) {

        String sql = "{CALL listar_vehiculos_de_un_cliente(?)}";

        try (Connection miConexion = ConectorBD.dameConexion(); CallableStatement miSentencia = miConexion.prepareCall(sql)) {

            miSentencia.setInt(1, dni_cliente);

            List<Vehiculo> lista = new ArrayList<>();

            try (ResultSet rs = miSentencia.executeQuery()) {

                while (rs.next()) {

                    Vehiculo vehiculo = Vehiculo.builder()
                            .id_vehiculo(rs.getInt("id_vehiculo"))
                            .marca(rs.getString("marca"))
                            .modelo(rs.getString("modelo"))
                            .anio(rs.getInt("anio"))
                            .build();

                    lista.add(vehiculo);

                }

            }

            return lista;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }

    }

}
