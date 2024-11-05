/*package com.practica.apptpi.controladores;

import com.practica.apptpi.dao.ClienteDAO;
import com.practica.apptpi.dao.TurnoDAO;
import com.practica.apptpi.entidades.Cliente;
import com.practica.apptpi.entidades.Turno;
import java.util.*;

public class TurnoControlador {

    private TurnoDAO turnoDAO;
    private ClienteDAO clienteDAO;
    private Scanner sc;

    public TurnoControlador() {
        turnoDAO = new TurnoDAO();
        clienteDAO = new ClienteDAO();
        sc = new Scanner(System.in);
    }*/

    // usu del metodo: read
    /*public void listarTurnos(){
        
        List<Turno> listaTurnos = turnoDAO.read();
        
        System.out.printf(
                "%-16s %-16s %-16s %-16s %-16s %-16s %-16s %-16s %-16s %-16s %-16s %-16s%n",
                "ID_TURNO",
                "FECHA",
                "COSTO_TOTAL",
                "ESTADO",
                "ID_CLIENTE",
                "NOMBRE_CLIENTE",
                "APELLIDO_CLIENTE",
                "ID_VEHICULO",
                "MARCA",
                "MODELO",
                "ID_SERVICIO",
                "NOMBRE_SERVICIO"
        );
        
        listaTurnos.stream()
                .map(p -> String.format(
                        "%-16s %-16s %-16s %-16s %-16s %-16s %-16s %-16s %-16s %-16s %-16s %-16s", 
                        p.getId_turno(),
                        p.getFecha(),
                        p.getCosto_total(),
                        p.isEstado(),
                        p.getCliente().getId_cliente(),
                        p.getCliente().getUsuario().getNombre(),
                        p.getCliente().getUsuario().getApellido(),
                        p.getVehiculo().getId_vehiculo(),
                        p.getVehiculo().getMarca(),
                        p.getVehiculo().getModelo(),
                        p.getServicio().getId_servicio(),
                        p.getServicio().getNombre()))
                .forEach(System.out::println);
        
        System.out.println("");
        
    }*/
    /*public void listarTurnos() {

        List<Turno> listaTurnos = turnoDAO.read();

        System.out.printf(
                "%-16s %-16s %-16s %-16s %-16s %-16s %-16s %-16s%n",
                "ID_CLIENTE",
                "NOMBRE",
                "FECHA_TURNO",
                "COSTO_TOTAL",
                "ESTADO",
                "MARCA",
                "MODELO",
                "SERVICIO_SOLICITADO"
        );

        listaTurnos.stream()
                .map(p -> String.format(
                "%-16s %-16s %-16s %-16s %-16s %-16s %-16s %-16s",
                p.getCliente().getId_cliente(),
                p.getCliente().getUsuario().getNombre(),
                p.getFecha(),
                p.getCosto_total(),
                p.isEstado(),
                p.getVehiculo().getMarca(),
                p.getVehiculo().getModelo(),
                p.getServicio().getNombre()))
                .forEach(System.out::println);

        System.out.println("");

    }

    // usu del metodo: listar turnos de un cliente en especifico
    public void mostrarTurnosDeUnCliente() {

        System.out.println("== VER LOS TURNOS DE UN CLIENTE ==");
        System.out.print("Digite el ID del cliente: ");
        int id = sc.nextInt();

        Cliente cliente = clienteDAO.searchById(id);

        if(cliente != null){
            
            System.out.println("\nTurnos del cliente: \"" + cliente.getUsuario().getNombre() + "\"");
            */
            // Opcional
            /*System.out.printf(
                    "%-16s %-16s %-25s %-20s %-16s%n",
                    "ID_CLIENTE",
                    "NOMBRE",
                    "CORREO",
                    "DOMICILIO",
                    "REGIMEN"
            );

            System.out.printf("%-16s %-16s %-25s %-20s %-16s",
                    cliente.getId_cliente(),
                    cliente.getUsuario().getNombre(),
                    cliente.getUsuario().getCorreo(),
                    cliente.getDomicilio(),
                    cliente.getTipoRegimenLaboral()
            );*/
            
            //System.out.println("");
            
            // imprimir turnos del cliente
            /*List<Turno> turnosDelCliente = turnoDAO.turnoDeCliente(cliente);
            
            System.out.printf(
                    "%-16s %-16s %-16s %-16s %-16s %-25s %-16s%n",
                    "ID_CLIENTE",
                    "NOMBRE",
                    "FECHA_TURNO",
                    "MARCA",
                    "MODELO",
                    "SERVICIO_SOLICITADO",
                    "PRECIO"
            );
            
            turnosDelCliente.stream()
                    .map(p -> String.format(
                            "%-16s %-16s %-16s %-16s %-16s %-25s %-16s", 
                            p.getCliente().getId_cliente(),
                            p.getCliente().getUsuario().getNombre(),
                            p.getFecha(),
                            p.getVehiculo().getMarca(),
                            p.getVehiculo().getModelo(),
                            p.getServicio().getNombre(),
                            p.getServicio().getCosto()))
                    .forEach(System.out::println);
            
            System.out.println("");
            
        }else{
            System.out.println("Error el cliente no existe");
        }

    }

}*/
