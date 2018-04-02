package co.quindio.sena.senasoftquindio2016.clases.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import co.quindio.sena.senasoftquindio2016.clases.vo.EmpresaVo;
import co.quindio.sena.senasoftquindio2016.clases.vo.ResultadosCategoriaVo;

/**
 * Created by CHENAO on 17/09/2016.
 */
public class ResultadosDao {

    public void insertarResultado(ResultadosCategoriaVo resultadosVo, SQLiteDatabase db){
        String consulta="INSERT INTO resultados (categoria_id, regional, centro, jornada1,jornada2,jornada3,total) " +
                "VALUES (" + resultadosVo.getCategoria() + ", '" + resultadosVo.getRegional() +"','" +resultadosVo.getNombreCentro() + "'," +resultadosVo.getResultadoDia1() + "," +resultadosVo.getResultadoDia2() + "," +resultadosVo.getResultadoDia3() + "," +resultadosVo.getResultadoTotal()+ ")";

      //  System.out.println(consulta);
        db.execSQL(consulta);

        //  db.close();
    }

    public void eliminarResultado(SQLiteDatabase db,String idResultado){
        String consulta="DELETE FROM resultados where categoria_id="+idResultado;

        try {
         //   System.out.println(consulta);
            db.execSQL(consulta);
        }catch (Exception e){
            e.printStackTrace();
        }
        //  db.close();
    }

    public ArrayList<ResultadosCategoriaVo> consultarListaResultado(SQLiteDatabase db,String idResultado) {
        ResultadosCategoriaVo resultadoVo;
        ArrayList<ResultadosCategoriaVo> listaResultado=new ArrayList<ResultadosCategoriaVo>();

        Cursor cursor = db.rawQuery("SELECT * FROM resultados where categoria_id="+idResultado, null);

        while (cursor.moveToNext()){
            resultadoVo=new ResultadosCategoriaVo();
            resultadoVo.setCategoria(cursor.getString(0));
            resultadoVo.setRegional(cursor.getString(1));
            resultadoVo.setNombreCentro(cursor.getString(2));
            resultadoVo.setResultadoDia1(cursor.getDouble(3));
            resultadoVo.setResultadoDia2(cursor.getDouble(4));
            resultadoVo.setResultadoDia3(cursor.getDouble(5));
            resultadoVo.setResultadoTotal(cursor.getDouble(6));
            listaResultado.add(resultadoVo);
        }
        cursor.close();
        return listaResultado;
    }

}
