package mx.lania.mrysi.iniciospring.control;

import java.util.Date;
import mx.lania.mrysi.iniciospring.entidades.Cliente;
import mx.lania.mrysi.iniciospring.oad.ClientesOad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author LANIA
 */
@Controller
public class ControladorClientes {
    
    @Autowired
    ClientesOad clientesOad;
    
    @RequestMapping("/clientes_old")
    public ModelAndView getCientes() {
        ModelAndView mav = new ModelAndView("clientes");
        mav.addObject("listaClientes", clientesOad.findAll());
        return mav;
    }
    
    @RequestMapping(value = "/altaclientes")
    public ModelAndView insertarCliente(
            @RequestParam("nombre") String nombre,
            @RequestParam("email") String email
    ) {
        ModelAndView mav = new ModelAndView("clientes");
        Cliente c = new Cliente();
        c.setNombre(nombre);
        c.setEmail(email);
        c.setFechaRegistro(new Date());
        clientesOad.save(c);
        mav.addObject("listaClientes", clientesOad.findAll());
        return mav;
    }
    
    @RequestMapping(value = "/clientes_old/{id}")
    public ModelAndView getClientePorId(@PathVariable("id")Integer idCliente) {
        ModelAndView mav = new ModelAndView("cliente");
        mav.addObject("cliente", clientesOad.findOne(idCliente));
        return mav;
    }
}





