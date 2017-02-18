package mx.lania.mrysi.iniciospring.servicios;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import mx.lania.mrysi.iniciospring.entidades.Cuenta;
import mx.lania.mrysi.iniciospring.oad.ClientesOad;
import mx.lania.mrysi.iniciospring.oad.CuentasOad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LANIA
 */
@Service
public class ServicioCuentas {

    @Autowired
    CuentasOad cuentasOad;

    @Autowired
    ClientesOad clientesOad;

    public void borrar(Integer idCuenta) {
        // AQUI VA UNA OPERACION COMPLEJA
        cuentasOad.delete(idCuenta);
    }

    public void crearCuenta(Cuenta cuenta) {
        cuenta.setFechaApertura(new Date());
        cuenta.setEstatus("ACTIVA");
        cuentasOad.save(cuenta);
    }
    
    public List<Cuenta> buscarCuentasNuevosClientes(int meses) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1*meses);
        return cuentasOad.buscarCuentasClientesRecientes(cal.getTime());
    }
    
}



