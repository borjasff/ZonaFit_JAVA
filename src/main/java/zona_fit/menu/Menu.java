package zona_fit.menu;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        menu();
    }
    private static void menu(){
        var salir = false;
        var consola = new Scanner(System.in);
        IClienteDAO clienteDAO = new ClienteDAO();
        //creamos un objeto de la clase cleinteDAO
        var clienteDao = new ClienteDAO();
        while (!salir){
            try {
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(consola,opcion, clienteDao );

            } catch (Exception e){
                System.out.println("Error al ejecutar las opciones: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static int mostrarMenu(Scanner consola){
        System.out.print("""
                        ***Zona fit (Gym)")
                        1.Listar Clientes
                        2.Buscar Cliente
                        3.Agregar Cliente
                        4.Modificar Cliente
                        5.Eliminar Cliente
                        6.Salir
                        Selecciona una categoría:\s""");
        return Integer.parseInt(consola.nextLine());
    }
    private static boolean ejecutarOpciones(Scanner consola, int opcion, IClienteDAO clienteDao){
        var salir = false;
        switch (opcion){
            case 1 -> {
                System.out.println("--Listado de los clientes--");
                var clientes = clienteDao.listarClientes();
                clientes.forEach(System.out::println);
            }
            case 2 -> {
                System.out.println("--Buscar a un cliente--");
                System.out.println("Dime el ID del cliente");
                var numeroIdCliente = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(numeroIdCliente);
                var clienteEncontrado = clienteDao.buscarClientePorId(cliente);
                if(clienteEncontrado){
                    System.out.println("Cliente encontrado: " + cliente);
                } else {
                    System.out.println("Cliente NO encontrado: " + cliente);
                }
            }
            case 3-> {
                System.out.println("--Agregar nuevo cliente--");
                System.out.println("Dime el nombre del cliente");
                var nombre = consola.nextLine();
                System.out.println("Dime el apellido del cliente");
                var apellido = consola.nextLine();
                System.out.println("Dime la membresía del cliente");
                var membresia = Integer.parseInt(consola.nextLine());
                var nuevoCliente = new Cliente(nombre, apellido, membresia);
                var agregado = clienteDao.agregarCliente(nuevoCliente);
                if(agregado){
                    System.out.println("Cliente agregado: " + nuevoCliente);
                } else {
                    System.out.println("Cliente NO agregado: " + nuevoCliente);
                }
            }
            case 4-> {
                System.out.println("--Actualizar cliente--");
                System.out.println("Dime el ID del cliente");
                var idCliente = Integer.parseInt(consola.nextLine());
                System.out.println("Dime el nombre del cliente");
                var nombre = consola.nextLine();
                System.out.println("Dime el apellido del cliente");
                var apellido = consola.nextLine();
                System.out.println("Dime la membresía del cliente");
                var membresia = Integer.parseInt(consola.nextLine());
                var clienteActualizado = new Cliente(idCliente, nombre, apellido, membresia);
                var modificado = clienteDao.modificarCliente(clienteActualizado);
                if(modificado){
                    System.out.println("Cliente modificado: " + clienteActualizado);
                } else {
                    System.out.println("Cliente NO modificado: " + clienteActualizado);
                }
            }
            case 5-> {
                System.out.println("--Eliminar cliente--");
                System.out.println("Dime el ID del cliente");
                var idCliente = Integer.parseInt(consola.nextLine());
                var clienteEliminado = new Cliente(idCliente);
                var eliminado = clienteDao.eliminarCliente(clienteEliminado);
                if(eliminado){
                    System.out.println("Cliente eliminado: " + clienteEliminado);
                } else {
                    System.out.println("Cliente NO eliminado: " + clienteEliminado);
                }
            }
            case 6-> {
                System.out.println("--¡Hasta pronto!--");
                salir = true;
            }
            default -> System.out.println("Opcion no reconocida en el menu");
        }
        return salir;
    }
}
