package co.quindio.sena.senasoftquindio2016.clases.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by CHENAO on 31/07/2016.
 */
public class CategoriasVo implements Serializable {

    private int id;
    private String nombre;
    private String info;
    private int iconoId;
    private int imagenId;
    private int colorLogo;

    private ArrayList<TematicaCategoriaVo> listaTematicas;

    public CategoriasVo(int id, String nombre, String info, int imagenId, int iconoId, ArrayList<TematicaCategoriaVo> listaTematicas, int colorLogo){
        this.id=id;
        this.nombre=nombre;
        this.info=info;
        this.imagenId=imagenId;
        this.iconoId=iconoId;
        this.listaTematicas=listaTematicas;
        this.colorLogo=colorLogo;
    }

    public int getColorLogo() {
        return colorLogo;
    }

    public void setColorLogo(int colorLogo) {
        this.colorLogo = colorLogo;
    }

    public int getIconoId() {
        return iconoId;
    }

    public void setIconoId(int iconoId) {
        this.iconoId = iconoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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


    public ArrayList<TematicaCategoriaVo> getListaTematicas() {
        return listaTematicas;
    }

    public void setListaTematicas(ArrayList<TematicaCategoriaVo> listaTematicas) {
        this.listaTematicas = listaTematicas;
    }
}
