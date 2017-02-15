package mx.lania.mrysi.iniciospring.control;

import java.util.List;
import javax.servlet.http.HttpSession;
import mx.lania.mrysi.iniciospring.entidades.Cuenta;
import mx.lania.mrysi.iniciospring.oad.CuentasOad;
import mx.lania.mrysi.iniciospring.servicios.ServicioCuentas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LANIA
 */
@RestController
@RequestMapping("/cuentas")
public class ControladorRestCuentas {
    
    private static Logger logger = 
            LoggerFactory.getLogger(ControladorRestCuentas.class);

    @Autowired
    CuentasOad cuentasOad;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Cuenta> getCuentas() {
        logger.debug("getCuentas()");
        return cuentasOad.findAll();
    }
    
    @RequestMapping(value = "", method = RequestMethod.GET,
            params = {"meses"})
    public List<Cuenta> getCuentasNuevosClientes(
            @RequestParam("meses") int meses
    ) {
        logger.debug("getCuentasNuevosClientes({})", meses);
        List<Cuenta> lista = servCuentas.buscarCuentasNuevosClientes(meses);
        logger.debug("Se encontraron {} cuentas", lista.size());
        return lista;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Cuenta getCuentaPorId(
            @PathVariable("id") Integer idCuenta
    ) {
        return cuentasOad.findOne(idCuenta);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @Transactional
    public Cuenta crearCuenta(@RequestBody Cuenta cuenta, 
            HttpSession sesion) {
        servCuentas.crearCuenta(cuenta);
        sesion.setAttribute("ultimaCuenta", cuenta);
        return cuenta;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Cuenta actualizarCuenta(
            @RequestBody Cuenta cuenta,
            @PathVariable("id") Integer idCuenta
    ) {
        cuenta.setIdCuenta(idCuenta);
        cuentasOad.save(cuenta);
        return cuenta;
    }
    
    @Autowired
    ServicioCuentas servCuentas;
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void borrarCuenta(
            @PathVariable("id") Integer idCuenta
    ) {
        servCuentas.borrar(idCuenta);
    }
}
