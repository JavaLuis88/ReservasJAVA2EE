/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservas.beans;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import reservas.servlet.Controlador;
import reservas.servlet.InformacionControlador;

/**
 *
 * @author Fu-Manchu
 */
public class Controlador2 extends Controlador {

    public Controlador2() {

        super();
        System.out.println("Controlado2");

    }

    public void init(String metodo, InformacionControlador informacion) {

        try {
            this.getResponse().getOutputStream().println("Controaldor2");
        } catch (IOException ex) {
            Logger.getLogger(Controlador5.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
