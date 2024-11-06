package com.practica.apptpi.controladores;

import com.practica.apptpi.dao.ClienteDAO;
import com.practica.apptpi.entidades.Cliente;
import java.util.*;

public class ClienteControlador {

    private ClienteDAO clienteDAO;
    private Scanner sc;

    public ClienteControlador() {
        clienteDAO = new ClienteDAO();
        sc = new Scanner(System.in);
    }

    // usu del metodo: create
    public void registrarCliente(String rol) {

        System.out.println("== REGISTRATE COMO CLIENTE ==");

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

        // Opcional
        System.out.print("Domicilio (enter para omitir): ");
        String domicilio = sc.nextLine();

        System.out.print("Regimen Laboral: ");
        String regimen = sc.nextLine();

        Cliente cliente = Cliente.builder()
                .dni(dni)
                .nombre(nombre)
                .apellido(apellido)
                .contrasena(contrasena)
                .correo(correo)
                .telefono(telefono)
                .domicilio(domicilio)
                .RegimenLaboral(regimen)
                .rol("Cliente")
                .build();

        if (clienteDAO.create(cliente)) {
            System.out.println("Cliente \"" + cliente.getNombre() + "\" agregado con exito");
        }

    }

    // uso del metodo: read
    public void listarClientes(String rol) {

        System.out.println("== TODOS LOS CLIENTES ==");

        List<Cliente> listaClientes = clienteDAO.read();

        System.out.printf(
                "%-16s %-16s %-16s %-30s %-16s %-16s %-30s %-16s%n",
                "DNI",
                "NOMBRE",
                "APELLIDO",
                "CORREO",
                "TELEFONO",
                "FECHA_INGRESO",
                "DOMICILIO",
                "REGIMEN");

        listaClientes.stream().
                map(p -> String.format(
                        "%-16s %-16s %-16s %-16s %-30s %-16s %-16s %-30s %-16s", 
                        p.getDni(),
                        p.getNombre(),
                        p.getApellido(),
                        p.getCorreo(),
                        p.getTelefono(),
                        p.getFechaIngreso(),
                        p.getDomicilio(),
                        p.getRegimenLaboral()))
                .forEach(System.out::println);
        
        System.out.println("");

    }

    // usu del metodo: update
    public void modificarMisDatos(String rol) {

        System.out.println("== ACTUALIZA TUS DATOS ==");

        System.out.print("Digite su DNI: ");
        int dni = sc.nextInt();

        sc.nextLine();

        Cliente cliente = clienteDAO.searchByDni(dni);

        if (cliente != null) {

            System.out.println("Hola \"" + cliente.getNombre() + "\", estas por actualizar tus datos");

            System.out.print("Nueva contraseña: ");
            String nuevaContrasena = sc.nextLine();

            System.out.print("Nuevo Telefono: ");
            String nuevaTelefono = sc.nextLine();

            System.out.print("Nuevo Correo: ");
            String nuevaCorreo = sc.nextLine();

            System.out.print("Nueva Domicilio: ");
            String nuevaDomicilio = sc.nextLine();

            cliente.setContrasena(nuevaContrasena);
            cliente.setTelefono(nuevaTelefono);
            cliente.setCorreo(nuevaCorreo);
            cliente.setDomicilio(nuevaDomicilio);

            if (clienteDAO.update(cliente)) {
                System.out.println("\"" + cliente.getNombre() + "\" has actualizado correctamente tus datos");
            } else {
                System.out.println("Error al actualizar");
            }

        } else {
            System.out.println("Error: el cliente no existe");
        }

    }

    // usu del metodo: delete
    // no importa el rol, ya que tanto los clientes como mecanicos pueden usarlo
    public void eliminarMiCuenta() {

        System.out.println("== ELIMINAR CUENTA DE CLIENTE ==");

        System.out.print("Digite su DNI: ");
        int dni = sc.nextInt();

        Cliente cliente = clienteDAO.searchByDni(dni);

        if (cliente != null) {

            if (clienteDAO.delete(cliente)) {
                System.out.println("Cliente \"" + cliente.getNombre() + "\" has eliminado tu cuenta correctamente");
            }

        } else {
            System.out.println("Error: el cliente no existe");
        }

    }

    // usu del metodo: searchByDni
    // distintos casos para diferentes roles
    public void verDatos(String rol) {

        System.out.println("== MOSTRAR TODA TU INFORMACION ==");
        System.out.print("Digite su DNI: ");
        int dni = sc.nextInt();

        Cliente cliente = clienteDAO.searchByDni(dni);

        // En caso de que un mecanico quiera ver los datos de un cliente
        if (cliente != null) {

            if (rol.equalsIgnoreCase("Cliente")) {

                System.out.printf(
                        "%-16s %-16s %-16s %-16s %-30s %-16s %-30s %-16s%n",
                        "DNI",
                        "NOMBRE",
                        "APELLIDO",
                        "CONTRASEÑA",
                        "CORREO",
                        "TELEFONO",
                        "DOMICILIO",
                        "REGIMEN");

                System.out.printf(
                        "%-16s %-16s %-16s %-16s %-30s %-16s %-16s %-30s %-16s",
                        cliente.getDni(),
                        cliente.getNombre(),
                        cliente.getApellido(),
                        cliente.getContrasena(),
                        cliente.getCorreo(),
                        cliente.getTelefono(),
                        cliente.getDomicilio(),
                        cliente.getRegimenLaboral()
                );

                System.out.println("");

            } else if (rol.equalsIgnoreCase("Mecanico")) {

                System.out.printf(
                        "%-16s %-16s %-16s %-30s %-16s %-16s %-30s %-16s%n",
                        "DNI",
                        "NOMBRE",
                        "APELLIDO",
                        "CORREO",
                        "TELEFONO",
                        "FECHA_INGRESO",
                        "DOMICILIO",
                        "REGIMEN");

                System.out.printf(
                        "%-16s %-16s %-16s %-16s %-30s %-16s %-16s %-30s %-16s",
                        cliente.getDni(),
                        cliente.getNombre(),
                        cliente.getApellido(),
                        cliente.getCorreo(),
                        cliente.getTelefono(),
                        cliente.getFechaIngreso(),
                        cliente.getDomicilio(),
                        cliente.getRegimenLaboral()
                );

                System.out.println("");

            }

        } else {
            System.out.println("Error: el cliente no existe");
        }

    }

}
