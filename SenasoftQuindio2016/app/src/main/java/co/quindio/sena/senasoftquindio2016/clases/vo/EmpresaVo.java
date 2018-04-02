package co.quindio.sena.senasoftquindio2016.clases.vo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.Serializable;

/**
 * Created by CHENAO on 4/08/2016.
 */
public class EmpresaVo implements Serializable{

    private int id;
    private String nombreEmpresa;
    private String descripcion;
    private int imagenId;
    private Bitmap logo;
    private String data;

    public EmpresaVo(int id, String nombreEmpresa, String descripcion, int imagenId) {
        this.id = id;
        this.nombreEmpresa = nombreEmpresa;
        this.descripcion = descripcion;
        this.imagenId = imagenId;
    }

    public EmpresaVo() {

    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
        try {
            byte[] byteData= Base64.decode(data,Base64.DEFAULT);
            //////
            int h = 150; // height in pixels
            int w = 150; // width in pixels
            Bitmap photoBitMap =  BitmapFactory.decodeByteArray(byteData,0,byteData.length);
            this.logo = Bitmap.createScaledBitmap(photoBitMap,h, w, true);

            // this.logo= BitmapFactory.decodeByteArray(byteData,0,byteData.length);
            //////

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Bitmap getLogo() {
        return logo;
    }

    public void setLogo(Bitmap logo) {
        this.logo = logo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImagenId() {
        return imagenId;
    }

    public void setImagenId(int imagenId) {
        this.imagenId = imagenId;
    }
}
