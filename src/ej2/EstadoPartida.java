package ej2;

public class EstadoPartida {

    private int vidaRestante;
    private int pantallaActual;


    public EstadoPartida(int vidaRestante, int pantallaActual) {
        this.vidaRestante = vidaRestante;
        this.pantallaActual = pantallaActual;
    }

    public EstadoPartida() {
    }

    public int getVidaRestante() {
        return vidaRestante;
    }

    public void setVidaRestante(int vidaRestante) {
        this.vidaRestante = vidaRestante;
    }

    public int getPantallaActual() {
        return pantallaActual;
    }

    public void setPantallaActual(int pantallaActual) {
        this.pantallaActual = pantallaActual;
    }

    @Override
    public String toString() {
        return "EstadoPartida{" +
                "vidaRestante=" + vidaRestante +
                ", pantallaActual=" + pantallaActual +
                '}';
    }


}
