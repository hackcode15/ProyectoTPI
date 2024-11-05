// NO HARIA FALTA

//package com.practica.apptpi.controladores;

/*import java.util.*;
import com.practica.apptpi.dao.UsuarioDAO;
import com.practica.apptpi.entidades.Usuario;*/

/*
Controlador:

Maneja la lógica
Coordina entre la clase DAO
Valida datos
Manejo de errores
*/

/*public class UsuarioControlador {

    private UsuarioDAO usuarioDAO;
    private Scanner sc;
    
    public UsuarioControlador(){
        this.usuarioDAO = new UsuarioDAO();
        this.sc = new Scanner(System.in);
    }
    
    // CREATE - Metodo para agregar
    public void agregarUsuario(){
        
        System.out.println("== Agregar Usuario ==");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido (enter para omitir): ");
        String apellido = sc.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();
        System.out.print("Correo: ");
        String correo = sc.nextLine();
        System.out.print("Telefono (enter para omitir): ");
        String telefono = sc.nextLine();*/
        
        // Forma normal
        /*Usuario usuario = new Usuario();
        
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setContrasena(contrasena);
        usuario.setCorreo(correo);
        usuario.setTelefono(telefono);*/
        
        // Usando Builder de lombok
        /*Usuario usuario = Usuario.builder()
                .nombre(nombre)
                .apellido(apellido)
                .apellido(apellido)
                .contrasena(contrasena)
                .correo(correo)
                .telefono(telefono)
                .build();
        
        if(usuarioDAO.create(usuario)){
            System.out.println("\nUsuario agregado con exito");
        }else{
            System.out.println("Error");
        }
            
    }
    
    // READ - Metodo para listar los usuarios
    public void listarUsuarios(){
        
        List<Usuario> lista = usuarioDAO.read();
        System.out.printf("%-16s %-16s %-16s %-16s %-30s %-16s", "ID USUARIO", "NOMBRE", "APELLIDO", "CONTRASEÑA", "CORREO", "TELEFONO");
        System.out.println("");
        lista.stream()
                .map(p -> String.format("%-16s %-16s %-16s %-16s %-30s %-16s", p.getId_usuario(), p.getNombre(), p.getApellido(), p.getContrasena(), p.getCorreo(), p.getTelefono()))
                .forEach(System.out::println);
        System.out.println("");
        
    }
    
    // UPDATE - Metodo para actualizar
    public void actualizarUsuario(){
        
        System.out.println("== Actualizar Usuario ==");
        System.out.print("Digite el ID: ");
        int id = sc.nextInt();
        
        Usuario usuario = usuarioDAO.searchById(id);
        
        if(usuario != null){
            
            sc.nextLine();
            System.out.print("Nueva contraseña: ");
            String contrasena = sc.nextLine();
            System.out.print("Nuevo telefono: ");
            String telefono = sc.nextLine();
         
            usuario.setContrasena(contrasena);
            usuario.setTelefono(telefono);
            
            if(usuarioDAO.update(usuario)){
                System.out.println("Usuario actualizado con exito");
            }else{
                System.out.println("Error al actualizar");
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
        
        Usuario usuario = usuarioDAO.searchById(id);
        
        if(usuario != null){
            
            if(usuarioDAO.delete(usuario)){
                System.out.println("Usuario eliminado con exito");
            }else{
                System.out.println("Error al eliminar");
            }
            
        }else{
            System.out.println("No existe un usuario con ese ID");
        }
        
    }
    
}*/
