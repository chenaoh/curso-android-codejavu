package co.quindio.sena.senasoftquindio2016.clases.vo;

import java.io.Serializable;

/**
 * Created by CHENAO on 31/07/2016.
 */
public class AgendaDiaVo implements Serializable {

    private int id;
    private int orden;
    private String modalidad;
    private String tema;
    private String conferencista;
    private String fecha;
    private String hora;
    private String lugar;

    public AgendaDiaVo(){

    }

    public AgendaDiaVo(int id, int orden, String modalidad, String tema, String conferencista, String fecha, String hora, String lugar) {
        this.id = id;
        this.orden = orden;
        this.modalidad = modalidad;
        this.tema = tema;
        this.conferencista = conferencista;
        this.fecha = fecha;
        this.hora = hora;
        this.lugar=lugar;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getConferencista() {
        return conferencista;
    }

    public void setConferencista(String conferencista) {
        this.conferencista = conferencista;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }


    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

}
