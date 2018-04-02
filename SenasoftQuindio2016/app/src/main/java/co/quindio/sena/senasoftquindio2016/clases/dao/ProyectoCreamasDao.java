package co.quindio.sena.senasoftquindio2016.clases.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import co.quindio.sena.senasoftquindio2016.clases.vo.ProyectoCreamasVo;

/**
 * Created by CHENAO on 17/09/2016.
 */
public class ProyectoCreamasDao {

    public void insertarProyecto(ProyectoCreamasVo proyectoCreamasVo, SQLiteDatabase db){
        String consulta="INSERT INTO proyecto_creamas (proyecto_id, nombre, descripcion, logo) " +
                "VALUES (" + proyectoCreamasVo.getId() + ", '" + proyectoCreamasVo.getNombreProyecto()
                +"','"+ proyectoCreamasVo.getDescripcion() +"','" +proyectoCreamasVo.getData() + "')";

       System.out.println(consulta);
        db.execSQL(consulta);

        //  db.close();
    }

    public void eliminarProyecto(SQLiteDatabase db){
        String consulta="DELETE FROM proyecto_creamas";

      //  System.out.println(consulta);
        db.execSQL(consulta);

        //  db.close();
    }

    public ArrayList<ProyectoCreamasVo> consultarListaProyecto(SQLiteDatabase db) {
        ProyectoCreamasVo proyectoCreamasVo;
        ArrayList<ProyectoCreamasVo> resultado=new ArrayList<ProyectoCreamasVo>();

        Cursor cursor = db.rawQuery("SELECT * FROM proyecto_creamas ", null);

        while (cursor.moveToNext()){
            proyectoCreamasVo=new ProyectoCreamasVo();
            proyectoCreamasVo.setId(cursor.getInt(0));
            proyectoCreamasVo.setNombreProyecto(cursor.getString(1));
            proyectoCreamasVo.setDescripcion(cursor.getString(2));
            proyectoCreamasVo.setData(cursor.getString(3));
            resultado.add(proyectoCreamasVo);
        }
        cursor.close();
        return resultado;
    }
}
