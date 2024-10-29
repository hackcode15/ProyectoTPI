package com.practica.apptpi.conexionBD;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class ConectorBD {

    // mysql
    /*public static String DB_URL = "";
    public static String USER = "";
    public static String PASS = "";*/
 /*public static String[] dameDatos(){
        
        String ruta = "D:/Documents/NetBeansProjects/AppTPI/src/main/java/com/practica/apptpi/conexionBD/credenciales.txt";
        
        String datos[] = new String[2];
        
        try(FileReader archivo = new FileReader(ruta);
                BufferedReader miBuffer = new BufferedReader(archivo)){
            
            String linea;
            
            for(int i=0; i<datos.length; i++){
                
                linea = miBuffer.readLine();
                
                if(linea != null){
                    datos[i] = linea;
                }else{
                    break;
                }
                
            }
            
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
        return datos;
        
    }*/
 /*public static Connection dameConexion() throws SQLException {
        
        String datos[] = dameDatos();
        
        DB_URL = datos[0];
        USER = datos[1];
        
        return DriverManager.getConnection(DB_URL, USER, PASS);
        
    }*/
    
    // SQL Server
    private static final String SERVER_NAME = "localhost";
    private static final String DATABASE_NAME = "proyectoBD";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "1234";

    public static Connection dameConexion() throws SQLException {
        try {
            // Cargar el controlador JDBC de SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Configurar las propiedades de conexión
            Properties connectionProps = new Properties();
            connectionProps.setProperty("user", USERNAME);
            connectionProps.setProperty("password", PASSWORD);
            connectionProps.setProperty("encrypt", "true");
            connectionProps.setProperty("trustServerCertificate", "true");

            // Establecer la conexión
            return DriverManager.getConnection(
                "jdbc:sqlserver://" + SERVER_NAME + ";databaseName=" + DATABASE_NAME,
                connectionProps
            );
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error al cargar el controlador JDBC: " + e.getMessage());
        }
    }

}
