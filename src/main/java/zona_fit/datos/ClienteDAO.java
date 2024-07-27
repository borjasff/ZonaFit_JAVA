package zona_fit.datos;

import zona_fit.connection.Conexion;
import zona_fit.dominio.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static zona_fit.connection.Conexion.getConection;

public class ClienteDAO implements IClienteDAO{

    @Override
    public List<Cliente> listarClientes() {
            List<Cliente> clientes = new ArrayList<>();
            PreparedStatement ps;
            ResultSet rs;
            Connection con = getConection();
        var sql = "SELECT * FROM cliente ORDER BY id";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                var cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                clientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch (Exception e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }

        }
        return clientes;
    }

    @Override
    public boolean buscarClientePorId(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConection();
        var sql = "SELECT * FROM cliente WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            rs = ps.executeQuery();
            if(rs.next()){
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                return true;
           }

        } catch (Exception e) {
            System.out.println("Error al recuperar por id el clientes: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch (Exception e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }

        }
        return false;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConection();
        String sql = "INSERT INTO cliente(nombre, apellido, membresia) " + " VALUES(?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al agregar el clientes: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch (Exception e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }

        }
        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConection();
        String sql = "UPDATE cliente set nombre=?, apellido=?, membresia=? " + " WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.setInt(4, cliente.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al agregar el clientes: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch (Exception e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }

        }
        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConection();
        String sql = "DELETE FROM cliente WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar el clientes: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch (Exception e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }

        }
        return false;
    }

    public static void main(String[] args) {

        IClienteDAO clienteDAO = new ClienteDAO();

        //prueba de listar clientes
        //System.out.println("---Listar clientes---");
        //IClienteDAO clienteDao = new ClienteDAO();
        //var clientes = clienteDao.listarClientes();
        //clientes.forEach(System.out::println);

        //buscar por id
        /**var cliente1 = new Cliente(2);
        System.out.println("Cliente antes de la busqueda: " + cliente1 );
        var encontrado = clienteDAO.buscarClientePorId(cliente1);
        if(encontrado){
            System.out.println("Cliente encontrado: " + cliente1);
        } else {
            System.out.println("Cliente no encontrado: " + cliente1.getId());
        }**/

        //agregar cliente
        /**var nuevoCliente = new Cliente("Daniel", " Blanco", 20);
        var agregado = clienteDAO.agregarCliente(nuevoCliente);
        if(agregado){
            System.out.println("Cliente agregado " + nuevoCliente);

        } else {
            System.out.println("Error al agregar el cliente  " + nuevoCliente);
        }

        //Listar clientes
        System.out.println("-+-Listar clientes-+-");
        IClienteDAO clienteDao = new ClienteDAO();
        var clientes = clienteDao.listarClientes();**/

        //modificar cliente
        /**var modificarCliente = new Cliente(4,"Sasha", " Villegas", 200);
         var modificado = clienteDAO.modificarCliente(modificarCliente);
         if(modificado){
         System.out.println("Cliente actualizado " + modificarCliente);

         } else {
         System.out.println("Error al actualizar el cliente  " + modificarCliente);
         }

         //Listar clientes
         System.out.println("-+-Listar clientes-+-");
         IClienteDAO clienteDao = new ClienteDAO();
         var clientes = clienteDao.listarClientes();**/

        //borrar cliente
        var eliminarCliente = new Cliente(5);
         var eliminado = clienteDAO.eliminarCliente(eliminarCliente);
         if(eliminado){
         System.out.println("Cliente Eliminado " + eliminarCliente);

         } else {
         System.out.println("Error al actualizar el cliente  " + eliminarCliente);
         }

         //Listar clientes
         System.out.println("-+-Listar clientes-+-");
         IClienteDAO clienteDao = new ClienteDAO();
         var clientes = clienteDao.listarClientes();
    }
}
