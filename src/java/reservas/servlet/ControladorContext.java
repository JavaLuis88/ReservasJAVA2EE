/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservas.servlet;

import java.util.Hashtable;

/**
 *
 * @author Fu-Manchu
 */
public interface ControladorContext {

    public InformacionControlador getControladorPorNombre(String nombre);
    public InformacionControlador getControladorPorURL(String url);
    public InformacionControlador getControladorPorClase(String clase);
    public Hashtable<String,InformacionControlador> getControladores();

}
