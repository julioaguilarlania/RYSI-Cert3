package mx.lania.mrysi.iniciospring.oad;

import java.util.List;
import mx.lania.mrysi.iniciospring.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author LANIA
 */
public interface ClientesOad extends JpaRepository<Cliente, Integer> {
    
    public List<Cliente> findByNombreContainingIgnoreCase(String cadena);
    public List<Cliente> findByEmailStartingWith(String cadena);
}





