package reservas.servlet;

import java.io.Serializable;

public class InformacionControlador implements Serializable {

    private String nombre;
    private String url;
    private String clase;
    private boolean metodoget;
    private boolean metodopost;
    private Controlador controlador;

    public InformacionControlador() {

    }

    public void setNombre(String nombre) {

        this.nombre = new String(nombre);

    }

    public String getNombre() {

        return new String(this.nombre);
    }

    public void setUrl(String url) {

        this.url = new String(url);

    }

    public String getUrl() {

        return new String(this.url);
    }

    public void setClase(String clase) {

        this.clase = new String(clase);

    }

    public String getClase() {

        return new String(this.clase);
    }

    public void setMetodoget(boolean metodoget) {

        this.metodoget = metodoget;

    }

    public boolean getMetodoget() {

        return this.metodoget;

    }

    public void setMetodopost(boolean metodopost) {

        this.metodopost = metodopost;

    }

    public boolean getMetodopost() {

        return this.metodoget;

    }

    public void setControlador(Controlador controlador) {

        this.controlador = (Controlador)controlador.clone();

    }

    public Controlador getControlador() {

        return (Controlador)this.controlador.clone();

    }

}
