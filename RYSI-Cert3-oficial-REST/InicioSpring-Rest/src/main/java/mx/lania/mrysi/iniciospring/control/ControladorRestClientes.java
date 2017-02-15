package mx.lania.mrysi.iniciospring.control;

import java.util.Date;
import java.util.List;
import mx.lania.mrysi.iniciospring.entidades.Cliente;
import mx.lania.mrysi.iniciospring.oad.ClientesOad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LANIA
 */
@RestController
@RequestMapping("/clientes")
public class ControladorRestClientes {

    @Autowired
    ClientesOad clientesOad;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Cliente> getClientes() {
        return clientesOad.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Cliente getClientePorId(
            @PathVariable("id") Integer idCliente
    ) {
        return clientesOad.findOne(idCliente);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        cliente.setFechaRegistro(new Date());
        clientesOad.save(cliente);
        return cliente;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Cliente actualizarCliente(
            @RequestBody Cliente cliente,
            @PathVariable("id") Integer idCliente
    ) {
        cliente.setIdCliente(idCliente);
        clientesOad.save(cliente);
        return cliente;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void borrarCliente(
            @PathVariable("id") Integer idCliente
    ) {
        clientesOad.delete(idCliente);
    }
}
