package com.practica.apptpi.autenticacion;

import com.practica.apptpi.dao.UsuarioDAO;
import com.practica.apptpi.entidades.Usuario;
import java.util.*;

public class MenuPrincipal {
    
    private Scanner sc;
    private Autenticacion autenticacion;
    private UsuarioDAO usuarioDAO;
    
    public MenuPrincipal() { 
        sc = new Scanner(System.in); 
        autenticacion = new Autenticacion(); 
        usuarioDAO = new UsuarioDAO(); 
    }

    public void iniciar() {
        while (true) {
            System.out.println("\n=== SISTEMA DE GESTIÓN AUTOMOTRIZ ===");
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Registrarse");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    menuDeLogin();
                    break;
                case 2:
                    menuRegistro();
                    break;
                case 0:
                    System.out.println("Programa finalizado");
                    return;
                default:
                    System.out.println("Opción incorrecta");
            }
        }
    }
    
    private void menuDeLogin() {
        
        System.out.println("\n=== INICIAR SESIÓN ===");
        
        System.out.print("DNI: ");
        int dni = sc.nextInt();
        
        sc.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();
        
        String rolDelUsuario = Autenticacion.autenticarUsuario(dni, contrasena);
        
        if(rolDelUsuario == null){
            System.out.println("Error de autenticacion, dni o contraseña incorrectos");
            return;
        }
        
        Usuario usuario = usuarioDAO.searchByDni(dni);
        
        if(rolDelUsuario.equalsIgnoreCase("Cliente")){
            
            menuCliente();
            
        }else if(rolDelUsuario.equalsIgnoreCase("Mecanico")){
            
            menuMecanico();
            
        }
        
    }
    
    private void menuRegistro() {
        System.out.println("\n=== REGISTRARSE ===");
        System.out.println("1. Registrarse como Cliente");
        System.out.println("2. Registrarse como Mecanico");
        System.out.println("0. Volver");
        System.out.print("Seleccione una opción: ");
        int opcion = sc.nextInt();
        
        switch(opcion){
            
            case 1:
                System.out.println("Elejiste seleccionar como cliente");
                break;
            case 2:
                System.out.println("Elejiste seleccionar como mecanico");
                break;
            case 0:
                System.out.println("Volviendo al menu principal");
                break;
            default:
                System.out.println("Opcion incorrecta");
                break;
                
        }
        
        
        
    }

    private void menuMecanico() {
        while (true) {
            System.out.println("\n=== MENÚ MECÁNICO ===");
            System.out.println("1. Ver mis datos");
            System.out.println("2. Actualizar mis datos");
            System.out.println("3. Ver lista de clientes");
            System.out.println("4. Buscar cliente por DNI");
            System.out.println("0. Cerrar Sesion");
            System.out.print("Seleccione una opción: ");
            int opcion = sc.nextInt();
            
            switch (opcion) {
                case 1:
                    System.out.println("Elegiste ver mis datos");
                    break;
                case 2:
                    System.out.println("Elegiste actualizar mis datos");
                    break;
                case 3:
                    System.out.println("Elegiste veris ver lista de clientes");
                    break;
                case 4:
                    System.out.println("Elegiste buscar cliente");
                    break;
                case 0:
                    System.out.println("Cerrando sesion...");
                    return;
                default:
                    System.out.println("Opción incorrecta");
            }
        }
    }

    private void menuCliente() {
        while (true) {
            System.out.println("\n=== MENÚ CLIENTE ===");
            System.out.println("1. Ver mis datos");
            System.out.println("2. Actualizar mis datos");
            System.out.println("3. Eliminar mi cuenta");
            System.out.println("4. Ver mis turnos");
            System.out.println("5. Solicitar turno para servicio");
            System.out.println("0. Cerrar sesion");
            System.out.print("Seleccione una opción: ");
            int opcion = sc.nextInt();
            
            switch (opcion) {
                case 1:
                    System.out.println("Eligiste ver mis datos");
                    break;
                case 2:
                    System.out.println("Eligiste ver mis datos");
                    break;
                case 3:
                    System.out.println("Eligiste ver mis datos");
                    break;
                case 4:
                    System.out.println("Eligiste ver mis datos");
                    return;
                case 5:
                    System.out.println("Eligiste ver mis datos");
                    break;
                case 0:
                    System.out.println("Cerrando sesion...");
                    return;
                default:
                    System.out.println("Opción incorrecta");
                    break;
            }
        }
    }
    
}
