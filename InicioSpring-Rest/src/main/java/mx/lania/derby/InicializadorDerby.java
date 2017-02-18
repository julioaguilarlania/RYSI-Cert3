package mx.lania.derby;

import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jaguilar
 */
public class InicializadorDerby {
    
    private final static Logger logger = LoggerFactory.getLogger(InicializadorDerby.class);
    private void inicializarDerby() {
        logger.debug("inicializarDerby()");
        try {
            Class clase = Class.forName("org.apache.derby.drda.NetworkServerControl");
            logger.debug("Derby encontrado");            
            Object controlServidorDerby = clase.newInstance();
            Method startMethod = clase.getMethod("start", java.io.PrintWriter.class);
            startMethod.invoke(controlServidorDerby, new Object[] { null });
        }
        catch (ClassNotFoundException cnfe) {
            // Si Derby no esta en el classpath, no hay mas que hacer.
        }
        catch (NoSuchMethodException nsme) {
            logger.warn("Derby encontrado pero sin constructor esperado.", nsme);
        }
        catch (InstantiationException ie) {
            logger.warn("Error al instanciar controlador del servidor de Derby", ie);
        }
        catch (Exception ex) {
            logger.error("", ex);
        }
    }    
}
