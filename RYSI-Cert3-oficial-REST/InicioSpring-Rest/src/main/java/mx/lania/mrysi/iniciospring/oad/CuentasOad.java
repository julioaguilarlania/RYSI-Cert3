package mx.lania.mrysi.iniciospring.oad;

import java.util.Date;
import java.util.List;
import mx.lania.mrysi.iniciospring.entidades.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author LANIA
 */
public interface CuentasOad extends JpaRepository<Cuenta, Integer> {

    @Query(value = "SELECT c.* FROM cuentas c"
            + " INNER JOIN clientes cl ON c.id_cliente = cl.id_cliente"
            + " WHERE cl.fecha_registro > ?1",
            nativeQuery = true)
    public List<Cuenta> buscarCuentasClientesRecientes(Date fecha);

    @Query(value = "SELECT c.* FROM cuentas c"
            + " INNER JOIN clientes cl ON c.id_cliente = cl.id_cliente"
            + " WHERE cl.fecha_registro > :fecha",
            nativeQuery = true)
    public List<Cuenta> busquedaConOtroPasoDeParametros(
            @Param("fecha") Date fecha
    );

    // Misma consulta en JPQL
    /*
    @Query(value = "SELECT c FROM Cuenta c"
            + " INNER JOIN Cliente cl ON c.idCliente = cl.idCliente"
            + " WHERE cl.fechaRegistro > ?1")
*/
    @Query(value = "SELECT c FROM Cuenta c"
            + " WHERE c.cliente.fechaRegistro > ?1")
    public List<Cuenta> busquedaEnJPQL(Date fecha);
}
