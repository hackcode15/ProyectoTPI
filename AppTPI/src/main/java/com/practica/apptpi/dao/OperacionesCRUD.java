package com.practica.apptpi.dao;

import java.util.List;

public abstract class OperacionesCRUD <T> {
    
    public abstract boolean create(T entidad);
    public abstract List<T> read();
    public abstract boolean update(T entidad);
    public abstract boolean delete(T entidad);
    public abstract T searchById(int id);
    
}
