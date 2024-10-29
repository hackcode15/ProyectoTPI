package com.practica.apptpi;

import com.practica.apptpi.controladores.UsuarioControlador;

public class Principal {

    public static void main(String[] args) {

        UsuarioControlador usuCon = new UsuarioControlador();

        usuCon.listarUsuarios();

        //usuCon.agregarUsuario();
        
        //usuCon.actualizarUsuario();
        
        //usuCon.eliminarUsuario();
        
    }

}
