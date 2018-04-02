package co.quindio.sena.senasoftquindio2016.clases.vo;

import java.io.Serializable;

/**
 * Created by CHENAO on 20/08/2016.
 */
public class ResultadosVo implements Serializable {

    private int id;
    private String nombreCategoria;
    private String info;
    private int imagenId;
    private int iconoId;
    private int colorLogo;

    public ResultadosVo(int id, String nombreCategoria, String info, int imagenId,int iconoId, int colorLogo) {
        this.id = id;
        this.nombreCategoria = nombreCategoria;
        this.info = info;
        this.imagenId = imagenId;
        this.iconoId=iconoId;
        this.colorLogo=colorLogo;
    }

    public int getIconoId() {
        return iconoId;
    }

    public void setIconoId(int iconoId) {
        this.iconoId = iconoId;
    }

    public int getColorLogo() {
        return colorLogo;
    }

    public void setColorLogo(int colorLogo) {
        this.colorLogo = colorLogo;
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
}
