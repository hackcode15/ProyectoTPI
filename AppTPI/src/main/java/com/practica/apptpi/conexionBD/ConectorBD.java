package com.practica.apptpi.conexionBD;

import java.io.*;
import java.sql.*;

public class ConectorBD {
    
    public static String DB_URL = "";
    public static String USER = "";
    public static String PASS = "";
    
    public static String[] dameDatos(){
        
        String ruta = "D:/Documents/NetBeansProjects/AppTPI/src/main/java/com/practica/apptpi/conexionBD/credenciales.txt";
        
        String datos[] = new String[3];
        
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
        
    }
    
    public static Connection dameConexion() throws SQLException {
        
        String datos[] = dameDatos();
        
        DB_URL = datos[0];
        USER = datos[1];
        PASS = datos[2];
        
        return DriverManager.getConnection(DB_URL, USER, PASS);
        
    }
    
    
}
