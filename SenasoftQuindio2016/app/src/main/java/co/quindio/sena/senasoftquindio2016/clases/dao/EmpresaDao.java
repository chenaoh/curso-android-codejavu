package co.quindio.sena.senasoftquindio2016.clases.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import co.quindio.sena.senasoftquindio2016.clases.vo.EmpresaVo;

/**
 * Created by CHENAO on 17/09/2016.
 */
public class EmpresaDao {

    public void insertarEmpresa(EmpresaVo empresaVo, SQLiteDatabase db){
        String consulta="INSERT INTO empresa (empresa_id, nombre, logo) " +
                "VALUES (" + empresaVo.getId() + ", '" + empresaVo.getNombreEmpresa() +"','" +empresaVo.getData() + "')";

      //  System.out.println(consulta);
        db.execSQL(consulta);

        //  db.close();
    }

    public void eliminarEmpresa(SQLiteDatabase db){
        String consulta="DELETE FROM empresa";

     //   System.out.println(consulta);
        db.execSQL(consulta);

        //  db.close();
    }

    public ArrayList<EmpresaVo> consultarListaEmpresas(SQLiteDatabase db) {
        EmpresaVo agendaVo;
        ArrayList<EmpresaVo> resultado=new ArrayList<EmpresaVo>();

        Cursor cursor = db.rawQuery("SELECT * FROM empresa ", null);

        while (cursor.moveToNext()){
            agendaVo=new EmpresaVo();
            agendaVo.setId(cursor.getInt(0));
            agendaVo.setNombreEmpresa(cursor.getString(1));
            agendaVo.setData(cursor.getString(2));
            resultado.add(agendaVo);
        }
        cursor.close();
        return resultado;
    }

}
