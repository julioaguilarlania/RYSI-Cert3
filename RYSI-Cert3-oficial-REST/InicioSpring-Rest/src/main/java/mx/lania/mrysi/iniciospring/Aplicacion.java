/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.lania.mrysi.iniciospring;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *
 * @author jaguilar
 */
public class Aplicacion implements WebApplicationInitializer {

    private final static Logger logger = LoggerFactory.getLogger(Aplicacion.class);

    @Override
    public void onStartup(ServletContext contenedor) throws ServletException {
        logger.debug("onStartup()");
        WebApplicationContext contextoSpring = getContext();
        ServletRegistration.Dynamic servlet = contenedor.addServlet("despachador", new DispatcherServlet(contextoSpring));
        servlet.setLoadOnStartup(2);
        servlet.addMapping("/");
    }

    private WebApplicationContext getContext() {
        logger.debug("Contexto de Spring");
        AnnotationConfigWebApplicationContext contexto = new AnnotationConfigWebApplicationContext();
        String nombreClase = this.getClass().getCanonicalName();
        String paqueteClase = nombreClase.substring(0, nombreClase.lastIndexOf('.'));
        logger.trace("Paquete: {}", paqueteClase);
        contexto.setConfigLocation(paqueteClase + ".config");
        return contexto;
    }
}
