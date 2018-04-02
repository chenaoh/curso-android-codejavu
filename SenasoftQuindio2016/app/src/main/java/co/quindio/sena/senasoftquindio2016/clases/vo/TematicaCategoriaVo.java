package co.quindio.sena.senasoftquindio2016.clases.vo;

import java.io.Serializable;

/**
 * Created by CHENAO on 7/08/2016.
 */
public class TematicaCategoriaVo implements Serializable {

    private int id;
    private String nombreCategoria;
    private String jornada;
    private String proceso;
    private String descripcion1;

    public TematicaCategoriaVo(int id, String nombreCategoria, String jornada, String proceso, String descripcion1) {
        this.id = id;
        this.nombreCategoria = nombreCategoria;
        this.jornada = jornada;
        this.proceso = proceso;
        this.descripcion1 = descripcion1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public String getDescripcion1() {
        return descripcion1;
    }

    public void setDescripcion1(String descripcion1) {
        this.descripcion1 = descripcion1;
    }

}
