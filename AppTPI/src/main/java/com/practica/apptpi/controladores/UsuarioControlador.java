package com.practica.apptpi.controladores;

import java.util.*;
import com.practica.apptpi.dao.UsuarioDAO;
import com.practica.apptpi.entidades.Usuario;

/*
Controlador:

Maneja la lógica
Coordina entre la clase DAO
Valida datos
Manejo de errores
*/

public class UsuarioControlador {

    private UsuarioDAO usuarioDAO;
    private Scanner sc;
    
    public UsuarioControlador(){
        this.usuarioDAO = new UsuarioDAO();
        this.sc = new Scanner(System.in);
    }
    
    /*
    C
    R
    U
    D
    */
    
    // CREATE - Metodo para agregar
    public void agregarUsuario(){
        
        System.out.println("== Agregar Usuario ==");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();
        System.out.print("Correo (enter para omitir): ");
        String correo = sc.nextLine();
        System.out.print("¿Es administrador? (Si/No): ");
        String esAdmin = sc.next();
        
        Usuario usuario = new Usuario();
        
        usuario.setNombre(nombre);
        usuario.setContrasena(contrasena);
        usuario.setCorreo(correo);
        
        if(esAdmin.equalsIgnoreCase("si")){
            usuario.setEsAdmin(true);
        }else{
            usuario.setEsAdmin(false);
        }
        
        if(usuarioDAO.agregar_usuario(usuario)){
            System.out.println("\nUsuario agregado con exito");
        }else{
            System.out.println("Error");
        }
            
    }
    
    // READ - Metodo para listar los usuarios
    public void listarUsuarios(){
        
        List<Usuario> lista = usuarioDAO.listar_usuarios();
        System.out.printf("%-16s %-16s %-16s %-30s %-16s%n", "ID USUARIO", "NOMBRE", "CONTRASEÑA", "CORREO", "ES ADMIN");
        lista.stream()
                .map(p -> String.format("%-16s %-16s %-16s %-30s %-16s", p.getId_usuario(), p.getNombre(), p.getContrasena(), p.getCorreo(), p.isEsAdmin()))
                .forEach(System.out::println);
        System.out.println("");
        
    }
    
    // UPDATE - Metodo para actualizar
    public void actualizarUsuario(){
        
        System.out.println("== Actualizar Usuario ==");
        System.out.print("Digite el ID: ");
        int id = sc.nextInt();
        
        Usuario usuario = usuarioDAO.buscar_por_id(id);
        
        if(usuario != null){
            
            sc.nextLine();
            System.out.print("Nueva contraseña: ");
            String contrasena = sc.nextLine();
            System.out.print("¿Es administrador? (Si/No): ");
            String esAdmin = sc.next();
         
            usuario.setContrasena(contrasena);
            if(esAdmin.equalsIgnoreCase("si")){
                usuario.setEsAdmin(true);
            }else{
                usuario.setEsAdmin(false);
            }
            
            if(usuarioDAO.actualizar_usuario(usuario)){
                System.out.println("Usuario actualizado con exito");
            }else{
                System.out.println("Error");
            }
            
        }else{
            System.out.println("Error no existe un usuario con ese ID");
        }
        
    }
    
    // DELETE - Metodo para eliminar usuario
    public void eliminarUsuario(){
        
        System.out.println("== Eliminar Usuario ==");
        System.out.print("Digite el ID: ");
        int id = sc.nextInt();
        
        Usuario usuario = usuarioDAO.buscar_por_id(id);
        
        if(usuario != null){
            
            usuarioDAO.eliminar_usuario(usuario);
            System.out.println("Usuario eliminado con exito");
            
        }else{
            System.out.println("No existe un usuario con ese ID");
        }
        
    }
    
}
