package com.practica.apptpi.dao;

import com.practica.apptpi.crud.OperacionesCrudAdaptadora;
import com.practica.apptpi.conexionBD.ConectorBD;
import com.practica.apptpi.crud.OperacionesCrud;
import com.practica.apptpi.entidades.Vehiculo;
import java.sql.*;
import java.util.*;

public class VehiculoDAO implements OperacionesCrud<Vehiculo>{

    @Override
    public boolean create(Vehiculo entidad) {
        return false;
    }

    @Override
    public List<Vehiculo> read() {
        return null;
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
    public Vehiculo searchByDni(int dni) {
        return null;
    }

}
