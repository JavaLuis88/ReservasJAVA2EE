/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservas.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Hashtable;
import java.io.File;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.rmi.ServerException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;


/*
         *
         * @author Usuario01
 */
public class ControladorFrontal extends HttpServlet implements ControladorContext {

    private final static String NOMBREPARAMETROARCHIVOURL = "ArchivoDeControladores";
    private Hashtable<String, InformacionControlador> url;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String metodo)
            throws ServletException, IOException {

        URL archivo;
        DocumentBuilderFactory factoria;
        DocumentBuilder contructordocumento;
        Document documento;
        NodeList nombres;
        NodeList urls;
        NodeList clases;
        NodeList metodosget;
        NodeList metodospost;
        Node datos;
        Class clase;
        Constructor constructorcontrolador;
        Controlador controlador;
        InformacionControlador informacion;

        this.url = new Hashtable<String, InformacionControlador>();

        if (this.getInitParameter(this.NOMBREPARAMETROARCHIVOURL) != null && this.getInitParameter(this.NOMBREPARAMETROARCHIVOURL).trim().equals("") == false) {

            try {
                archivo = this.getClass().getResource(this.getInitParameter(this.NOMBREPARAMETROARCHIVOURL).trim());

                factoria = DocumentBuilderFactory.newInstance();

                contructordocumento = factoria.newDocumentBuilder();
                documento = contructordocumento.parse(archivo.openStream());
                documento.getDocumentElement().normalize();
                nombres = documento.getElementsByTagName("NOMBRE");
                urls = documento.getElementsByTagName("URL");
                clases = documento.getElementsByTagName("CLASE");
                metodosget = documento.getElementsByTagName("METODOGET");
                metodospost = documento.getElementsByTagName("METODOPOST");

                if (nombres != null && urls != null && clases != null && metodosget != null && metodospost != null && nombres.getLength() == urls.getLength() || nombres.getLength() == clases.getLength() || nombres.getLength() == metodosget.getLength() || nombres.getLength() == metodospost.getLength()) {
                    for (int i = 0; i < nombres.getLength(); i++) {
                        if (nombres.item(i).getNodeType() == 1) {

                            //if (nombres.item(i).getTextContent() != null && nombres.item(i).getTextContent().trim().equals("") == false && urls.item(i).getTextContent() != null && urls.item(i).getTextContent().trim().equals("") == false && clases.item(i).getTextContent() != null && clases.item(i).getTextContent().trim().equals("") == false && metodosget.item(i).getTextContent() != null && metodosget.item(i).getTextContent().trim().equals("") == false && metodospost.item(i).getTextContent() != null && metodospost.item(i).getTextContent().trim().equals("") == false && (metodosget.item(i).getTextContent().trim().equals("0") == true || metodosget.item(i).getTextContent().trim().equals("1") == true) && (metodospost.item(i).getTextContent().trim().equals("0") == true || metodospost.item(i).getTextContent().trim().equals("1") == true) && (metodosget.item(i).getTextContent().trim().equals("1") == true || metodospost.item(i).getTextContent().trim().equals("1") == true)) {
                            //boolean b=(metodosget.item(i).getTextContent().trim().equals("0")==true || metodosget.item(i).getTextContent().trim().equals("1") == true);
                            // b=(metodospost.item(i).getTextContent().trim().equals("0") == true || metodospost.item(i).getTextContent().trim().equals("1") == true); 
                            //b=(metodosget.item(i).getTextContent().trim().equals("1") == true || metodospost.item(i).getTextContent().trim().equals("1") == true);
                            if (nombres.item(i).getTextContent() != null && nombres.item(i).getTextContent().trim().equals("") == false && urls.item(i).getTextContent() != null && urls.item(i).getTextContent().trim().equals("") == false && clases.item(i).getTextContent() != null && clases.item(i).getTextContent().trim().equals("") == false && metodosget.item(i).getTextContent() != null && metodosget.item(i).getTextContent().trim().equals("") == false && metodospost.item(i).getTextContent() != null && metodospost.item(i).getTextContent().trim().equals("") == false && (metodosget.item(i).getTextContent().trim().equals("0") == true || metodosget.item(i).getTextContent().trim().equals("1") == true) && (metodospost.item(i).getTextContent().trim().equals("0") == true || metodospost.item(i).getTextContent().trim().equals("1") == true) && (metodosget.item(i).getTextContent().trim().equals("1") == true || metodospost.item(i).getTextContent().trim().equals("1") == true)) {

                                if (this.getControladorPorURL(urls.item(i).getTextContent()) == null) {

                                    clase = Class.forName(clases.item(i).getTextContent().trim());
                                    constructorcontrolador = clase.getConstructor(new Class[]{});
                                    controlador = (Controlador) constructorcontrolador.newInstance();

                                    if (controlador instanceof Controlador == true) {

                                        controlador.setRequest(request);
                                        controlador.setResponse(response);
                                        controlador.setContexto((ControladorContext) this);
                                        informacion = new InformacionControlador();
                                        informacion.setNombre(nombres.item(i).getTextContent().trim());
                                        informacion.setUrl(urls.item(i).getTextContent().trim());
                                        informacion.setClase(clases.item(i).getTextContent().trim());

                                        if (metodosget.item(i).getTextContent().trim().equals("1")==true) {

                                            informacion.setMetodoget(true);
                                        } else {

                                            informacion.setMetodoget(false);
                                        }

                                        if (metodospost.item(i).getTextContent().trim().equals("1")==true) {

                                            informacion.setMetodopost(true);
                                        } else {

                                            informacion.setMetodopost(false);
                                        }

                                        informacion.setControlador(controlador);

                                        this.url.put(nombres.item(i).getTextContent().trim().toUpperCase(), informacion);

                                    } else {
                                        throw new ServletException("Uno de los controaldores no herda de Controaldor");

                                    }

                                } else {
                                    throw new ServletException("Una URL no puede ser atendia por dos controaldores");

                                }

                            } else {

                                throw new ServletException("El formato de archivo de configuarcion no es valido2");

                            }

                        } else {
                            throw new ServletException("El formato de archivo de configuarcion no es valido");
                        }
                    }

                    if (this.getControladorPorURL(request.getServletPath()) != null) {

                        if ((metodo.trim().toUpperCase() == "GET" && this.getControladorPorURL(request.getServletPath()).getMetodoget() == true) || (metodo.trim().toUpperCase() == "POST" && this.getControladorPorURL(request.getServletPath()).getMetodopost() == true)) {

                            this.getControladorPorURL(request.getServletPath()).getControlador().init(metodo, this.getControladorPorURL(request.getServletPath()));
                        request.setAttribute("contexto", this.url);
                        } else {
                            response.sendError(HttpServletResponse.SC_NOT_FOUND, "La URL no es valida");

                        }

                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "La URL no es valida");

                    }
                } else {
                    throw new ServletException("El formato de archivo de configuarcion no es valido");

                }

            } catch (ParserConfigurationException ex) {

                throw new IOException("Error al leer el archivo de configuarcion");

            } catch (SAXException ex) {
                throw new ServletException("El formato de archivo de configuarcion no es valido");
            } catch (ClassNotFoundException ex) {
                throw new ServletException("Error al cargar controlador");

            } catch (NoSuchMethodException ex) {
                throw new ServletException("Error al cargar controlador2");
            } catch (SecurityException ex) {
                throw new ServletException("Error al cargar controlador3");
            } catch (InstantiationException ex) {
                throw new ServletException("Error al cargar controlador4");
            } catch (IllegalAccessException ex) {
                throw new ServletException("Error al cargar controlador5");
            } catch (IllegalArgumentException ex) {
                throw new ServletException("Error al cargar controlador6");
            } catch (InvocationTargetException ex) {
                throw new ServletException("Error al cargar controlador7");
            }
            //catch (URISyntaxException ex) {
            //    Logger.getLogger(ControladorFrontal.class.getName()).log(Level.SEVERE, null, ex);
            //}

        } else {

            throw new ServletException("El valor de parametro ArchivoDeControladores no es valido");

        }

    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response, "GET");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response, "POST");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    public InformacionControlador getControladorPorNombre(String nombre) {

        InformacionControlador infomacioncontroaldor;
        infomacioncontroaldor = null;

        if (this.url.get(nombre.trim().toUpperCase()) != null) {

            infomacioncontroaldor = (InformacionControlador) this.url.get(nombre.toUpperCase());

        }

        return infomacioncontroaldor;

    }

    @Override
    public InformacionControlador getControladorPorURL(String url) {

        Object claves[];
        InformacionControlador infomacioncontroaldor;
        int i;
        infomacioncontroaldor = null;

        claves = this.url.keySet().toArray();
        i = 0;

        while (i < claves.length && infomacioncontroaldor == null) {

            if (this.url.get((String) claves[i]).getUrl().equals(url.trim()) == true) {
                infomacioncontroaldor = this.url.get((String) claves[i]);
            }

            i++;

        }

        return infomacioncontroaldor;

    }

    @Override

    public InformacionControlador getControladorPorClase(String clase) {

        Object claves[];
        InformacionControlador infomacioncontroaldor;
        int i;
        infomacioncontroaldor = null;

        claves = this.url.keySet().toArray();
        i = 0;

        while (i < claves.length && infomacioncontroaldor == null) {

            if (this.url.get((String) claves[i]).getClase().equals(clase.trim()) == true) {
                infomacioncontroaldor = this.url.get((String) claves[i]);
            }

            i++;

        }

        return infomacioncontroaldor;

    }

    @Override
    public Hashtable<String, InformacionControlador> getControladores() {

        Hashtable<String, InformacionControlador> objeto;
        objeto = (Hashtable<String, InformacionControlador>) this.url.clone();

        return objeto;

    }

}
