package mx.lania.mrysi.iniciospring.control;

import java.math.BigInteger;
import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author LANIA
 */
@Controller
public class ControladorSaludos {
    
    @RequestMapping("/hola")
    public ModelAndView saludar() {
        ModelAndView mav = new ModelAndView("bienvenida");
        mav.addObject("saludo", "¡Hola mundo!");
        mav.addObject("tiempo", new Date());
        return mav;
    }
    
    @RequestMapping(value = "/sumar", method = RequestMethod.POST)
    public ModelAndView sumar(@RequestParam("a") BigInteger a, 
            @RequestParam("b") BigInteger b) {
        ModelAndView mav = new ModelAndView("suma");
        mav.addObject("a", a);
        mav.addObject("b", b);
        mav.addObject("suma", a.add(b));
        return mav;
    }
}






