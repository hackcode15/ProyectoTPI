package com.practica.apptpi.controladores;

import com.practica.apptpi.dao.ClienteDAO;
import com.practica.apptpi.entidades.Cliente;
import java.util.*;

public class ClienteControlador {

    private ClienteDAO clienteDAO;
    private Scanner sc;
    
    public ClienteControlador(){
        clienteDAO = new ClienteDAO();
        sc = new Scanner(System.in);
    }
    
    // usu del metodo - create
    public void registrarCliente(){
        
        System.out.println("== REGISTRAR CLIENTE ==");
        
        // Datos heredados de Usuario
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        
        System.out.print("Apellido: ");
        String apellido = sc.nextLine();
        
        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();
        
        System.out.print("Correo: ");
        String correo = sc.nextLine();
        
        System.out.print("Telefono: ");
        String telefono = sc.nextLine();
        
        // Datos propios de Cliente
        System.out.print("Domicilio: ");
        String domicilio = sc.nextLine();
        
        System.out.print("Regimen Laboral: ");
        String regimen = sc.nextLine();
        
        Cliente cliente = Cliente.builder()
                .nombre(nombre)
                .apellido(apellido)
                .contrasena(contrasena)
                .correo(correo)
                .telefono(telefono)
                .domicilio(domicilio)
                .tipoRegimenLaboral(regimen)
                .build();
        
        if(clienteDAO.create(cliente)){
            System.out.println("Cliente \"" + cliente.getNombre() + "\" agregado exitosamente");
        }else{
            System.out.println("Error al agregar el cliente");
        }
    
    }
    
    // usu del metodo - read
    public void listarClientes(){
        
        List<Cliente> listaClientes = clienteDAO.read();
        
        System.out.printf("%-16s %-16s %-30s %-16s %-16s %-16s%n", "NOMBRE", "APELLIDO", "CORREO", "TELEFONO", "DOMICILIO", "REGIMEN");
        
        listaClientes.stream()
                .map(p -> String.format(
                        "%-16s %-16s %-30s %-16s %-16s %-16s", 
                        p.getNombre(), 
                        p.getApellido(), 
                        p.getCorreo(), 
                        p.getTelefono(), 
                        p.getDomicilio(), 
                        p.getTipoRegimenLaboral()))
                .forEach(System.out::println);
        
        System.out.println("");
        
    }
    
    // usu del metodo: update
    public void actualizarCliente(){
        
        System.out.println("== ACTUALIZAR CLIENTE ==");
        
        System.out.print("Digite el ID del cliente: ");
        int id = sc.nextInt();
        
        Cliente cliente = clienteDAO.searchById(id);
        
        if(cliente != null){
            
            System.out.println("Actualizaras los datos del cliente \"" + cliente.getNombre() + "\"");
            
            sc.nextLine();
            System.out.print("Nueva contraseña: ");
            String nuevaContrasena = sc.nextLine();
            
            System.out.print("Nuevo telefono: ");
            String nuevoTelefono = sc.nextLine();
            
            cliente.setContrasena(nuevaContrasena);
            cliente.setTelefono(nuevoTelefono);
            
            if(clienteDAO.update(cliente)){
                System.out.println("Cliente \"" + cliente.getNombre() + "\" actualizado con exito");
            }else{
                System.out.println("Error al actualizar");
            }
            
        }else{
            System.out.println("Error: el cliente no existe");
        }
        
    }
    
    // usu del metodo: delete
    public void eliminarCliente(){
        
        System.out.println("== ELIMINAR CLIENTE ==");
        
        System.out.print("Digite el ID del cliente: ");
        int id = sc.nextInt();
        
        Cliente cliente = clienteDAO.searchById(id);
        
        if(cliente != null){
            
            if(clienteDAO.delete(cliente)){
                System.out.println("Cliente \"" + cliente.getNombre() + "\" eliminado correctamente");
            }else{
                System.out.println("Error al eliminar el cliente");
            }
            
        }else{
            System.out.println("Error: el cliente no existe");
        }
        
        
    }
    
    // usu del metodo: searchById
    public void buscarCliente(){
        
        System.out.println("== BUSCAR CLIENTE ==");
        System.out.print("Digite el ID del cliente: ");
        int id = sc.nextInt();
        
        Cliente cliente = clienteDAO.searchById(id);
        
        if(cliente != null){
            
            System.out.printf(
                    "%-16s %-16s %-25s %-20s %-16s%n",
                    "ID_CLIENTE",
                    "NOMBRE",
                    "CORREO",
                    "DOMICILIO",
                    "REGIMEN"
                    );
            
            System.out.printf("%-16s %-16s %-25s %-20s %-16s",
                    cliente.getId_cliente(),
                    cliente.getNombre(),
                    cliente.getCorreo(),
                    cliente.getDomicilio(),
                    cliente.getTipoRegimenLaboral()
            );
            
            System.out.println("");
            
        }else{
            System.out.println("Error: el cliente no existe");
        }
        
    }
    
    
}
