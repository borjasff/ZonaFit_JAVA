package zona_fit.menu;

import zona_fit.dominio.Cliente;

import java.util.List;

public interface IMenu {
    List<Cliente> listarClientes();
    boolean buscarClientePorId(Cliente cliente);
    boolean agregarClientePorId(Cliente cliente);
    boolean modificarClientePorId(Cliente cliente);
    boolean eliminarClientePorId(Cliente cliente);
}
