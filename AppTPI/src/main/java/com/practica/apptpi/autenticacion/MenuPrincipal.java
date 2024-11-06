package com.practica.apptpi.autenticacion;

import java.util.*;

public class MenuPrincipal {
    
    private Scanner sc;
    
    public MenuPrincipal(){
        sc = new Scanner(System.in);
    }

    public void iniciar() {
        while (true) {
            System.out.println("\n=== SISTEMA DE GESTIÓN AUTOMOTRIZ ===");
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    
                    break;
                case 2:
                    
                    break;
                case 3:
                    System.out.println("¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
    
    private void login() {
        System.out.println("\n=== INICIAR SESIÓN ===");
        
        System.out.print("DNI: ");
        String dni = sc.nextLine();
        
        System.out.print("Contraseña: ");
        String password = sc.nextLine();
        
        System.out.println("1- Cliente / 2 - Mecanico: ");
        int eleccion = sc.nextInt();
        
        String rol = (eleccion == 1) ? "Cliente" : "Mecanico";
        
        // ... falta metodo de autenticacion
        
    }
    
    private void menuRegistro() {
        System.out.println("\n=== REGISTRARSE ===");
        System.out.println("1. Registrarse como Cliente");
        System.out.println("1. Registrarse como Mecanico");
        System.out.println("2. Volver");
        System.out.print("Seleccione una opción: ");

        // ....
        
    }

    private void menuMecanico() {
        while (true) {
            System.out.println("\n=== MENÚ MECÁNICO ===");
            System.out.println("1. Ver mis datos");
            System.out.println("2. Actualizar mis datos");
            System.out.println("3. Ver lista de clientes");
            System.out.println("4. Buscar cliente por DNI");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            int opcion = sc.nextInt();
            
            switch (opcion) {
                case 1:
                    
                    break;
                case 2:
                    
                    break;
                case 3:
                    
                    break;
                case 4:
                    
                    break;
                case 5:
                    
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void menuCliente() {
        while (true) {
            System.out.println("\n=== MENÚ CLIENTE ===");
            System.out.println("1. Ver mis datos");
            System.out.println("2. Actualizar mis datos");
            System.out.println("3. Ver mecánicos disponibles");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            int opcion = sc.nextInt();
            
            
            switch (opcion) {
                case 1:
                    
                    break;
                case 2:
                    
                    break;
                case 3:
                    ;
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
    
}
