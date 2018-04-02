package co.quindio.sena.senasoftquindio2016.clases.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by CHENAO on 31/07/2016.
 */
public class AgendaVo implements Serializable {

    private int id;
    private String dia;
    private String info;
    private int imagenId;
    private ArrayList<AgendaDiaVo> listaAgendaDia;

    public AgendaVo(int id,String dia, String info, int imagenId,ArrayList<AgendaDiaVo> listaAgendaDia){
        this.id=id;
        this.dia=dia;
        this.info=info;
        this.imagenId=imagenId;
        this.listaAgendaDia=listaAgendaDia;
    }


    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getImagenId() {
        return imagenId;
    }

    public void setImagenId(int imagenId) {
        this.imagenId = imagenId;
    }

    public ArrayList<AgendaDiaVo> getListaAgendaDia() {
        return listaAgendaDia;
    }

    public void setListaAgendaDia(ArrayList<AgendaDiaVo> listaAgendaDia) {
        this.listaAgendaDia = listaAgendaDia;
    }
}
