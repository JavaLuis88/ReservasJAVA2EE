/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservas.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario01
 */
public class Controlador implements Cloneable {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private ControladorContext contexto;

    public Controlador() {

    }

    public final void setRequest(HttpServletRequest request) {

        this.request = request;

    }

    public final HttpServletRequest getRequest() {

        return this.request;

    }

    public final void setResponse(HttpServletResponse response) {

        this.response = response;

    }

    public final HttpServletResponse getResponse() {

        return this.response;

    }

    public final void setContexto(ControladorContext contexto) {

        this.contexto = contexto;

    }

    public final ControladorContext getContexto() {

        return this.contexto;

    }

    
    public void init(String metodo,InformacionControlador informacion) {
        
        
    } 
    
    
    @Override
    public Object clone() {

        Object objeto;

        objeto = null;

        try {

            objeto = super.clone();

        } catch (CloneNotSupportedException e) {

        }

        return objeto;

    }

}
