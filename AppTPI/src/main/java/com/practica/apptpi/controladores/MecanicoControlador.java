package com.practica.apptpi.controladores;

import com.practica.apptpi.dao.MecanicoDAO;
import com.practica.apptpi.modelo.Mecanico;
import java.util.*;

public class MecanicoControlador {
    
    private MecanicoDAO mecanicoDAO;
    private Scanner sc;
    
    public MecanicoControlador(){
        this.mecanicoDAO = new MecanicoDAO();
        this.sc = new Scanner(System.in);
    }
    
    // usu del metodo: create
    public void agregarMecanico(){
        
        System.out.println("== AGREGAR MECANICO ==");
        
        System.out.print("Digite su DNI: ");
        int dni = sc.nextInt();

        sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        // Opcional
        System.out.print("Apellido (enter para omitir): ");
        String apellido = sc.nextLine();

        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();

        System.out.print("Correo: ");
        String correo = sc.nextLine();

        System.out.print("Telefono: ");
        String telefono = sc.nextLine();
        
        System.out.print("Sueldo $: ");
        double sueldo = sc.nextDouble();
        
        Mecanico mecanico = Mecanico.builder()
                .dni(dni)
                .nombre(nombre)
                .apellido(apellido)
                .contrasena(contrasena)
                .correo(correo)
                .telefono(telefono)
                .sueldo(sueldo)
                .rol("Mecanico")
                .build();
        
        if(mecanicoDAO.create(mecanico)){
            System.out.println("Mecanico \"" + mecanico.getNombre() + "\" agregado correctamente");
        }
        
    }
    
    // usu del metodo: read
    public void listarMecanicos(){
        
        System.out.println("== LISTA DE MECANICOS ==");
        
        List<Mecanico> listaMecanicos = mecanicoDAO.read();
        
        System.out.printf(
                "%-16s %-16s %-16s %-16s %-16s %-16s%n",
                "DNI",
                "NOMBRE",
                "APELLIDO",
                "TELEFONO",
                "FECHA_INGRESO",
                "SUELDO");
        
        listaMecanicos.stream()
                .map(p -> String.format(
                        "%-16s %-16s %-16s %-16s %-16s %-16s", 
                        p.getDni(),
                        p.getNombre(),
                        p.getApellido(),
                        p.getTelefono(),
                        p.getFechaIngreso(),
                        "$" + p.getSueldo()))
                .forEach(System.out::println);
        
        System.out.println("");
        
    }
    
    // uso del metodo: update
    public void actualizarMecanico(){
        
        System.out.println("== ACTUALIZAR MECANICO ==");
        
        System.out.print("Digite el DNI: ");
        int dni = sc.nextInt();
        
        sc.nextLine();
        
        Mecanico mecanico = mecanicoDAO.searchByDni(dni);
        
        if(mecanico != null){
            
            System.out.println("Actualizaras los datos de \"" + mecanico.getNombre() + "\"");
            
            System.out.print("Nueva contraseña: ");
            String nuevaContrasena = sc.nextLine();
            
            System.out.print("Nuevo Telefono: ");
            String nuevaTelefono = sc.nextLine();
            
            System.out.print("Nuevo Correo: ");
            String nuevoCorreo = sc.nextLine();
            
            System.out.print("Nuevo sueldo $: ");
            double nuevoSueldo = sc.nextDouble();
            
            mecanico.setContrasena(nuevaContrasena);
            mecanico.setTelefono(nuevaTelefono);
            mecanico.setCorreo(nuevoCorreo);
            mecanico.setSueldo(nuevoSueldo);
            
            if(mecanicoDAO.update(mecanico)){
                System.out.println("Mecanico \"" + mecanico.getNombre() + "\" actualizado correctamente");
            }else{
                System.out.println("Error al actualizar");
            }
            
        }else{
            System.out.println("Error: el mecanico no existe");
        }
        
    }
    
    // uso del metodo: delete
    public void eliminarMecanico(){
        
        System.out.println("== ELIMINAR MECANICOS ==");
        
        System.out.print("Digite el DNI: ");
        int dni = sc.nextInt();
        
        Mecanico mecanico = mecanicoDAO.searchByDni(dni);
        
        if(mecanico != null){
            
            if(mecanicoDAO.delete(mecanico)){
                System.out.println("Mecanico \"" + mecanico.getNombre() + "\" eliminado ccrrectamente");
            }
            
        }else{
            System.out.println("Error el mecanico no existe");
        }
        
    }
    
    // usu del metodo: searchByDni
    public void buscarMecanico(){
        
        System.out.println("== BUSCAR MECANICO ==");
        
        System.out.print("Digite el DNI: ");
        int dni = sc.nextInt();
        
        Mecanico mecanico = mecanicoDAO.searchByDni(dni);
        
        if(mecanico != null){
            
            System.out.printf(
                "%-16s %-16s %-16s %-16s %-16s %-16s%n",
                "DNI",
                "NOMBRE",
                "APELLIDO",
                "TELEFONO",
                "FECHA_INGRESO",
                "SUELDO");
        
            System.out.printf(
                    "%-16s %-16s %-16s %-16s %-16s %-16s%n",
                    mecanico.getDni(),
                    mecanico.getNombre(),
                    mecanico.getApellido(),
                    mecanico.getTelefono(),
                    mecanico.getFechaIngreso(),
                    mecanico.getSueldo()
                    );

            System.out.println("");
            
        }else{
            System.out.println("Error: el mecanico no existe");
        }
        
    }
    
}