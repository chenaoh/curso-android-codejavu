package co.quindio.sena.senasoftquindio2016.clases.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import co.quindio.sena.senasoftquindio2016.clases.vo.AgendaDiaVo;

/**
 * Created by CHENAO on 10/09/2016.
 */
public class AgendaDiaDao {

    public void insertarAgenda(AgendaDiaVo agendaVo, SQLiteDatabase db){
        String consulta="INSERT INTO agenda (agenda_id, orden, modalidad, tema, conferencista, fecha, hora, lugar) " +
                "VALUES (" + agendaVo.getId() + ", '" + agendaVo.getOrden() +"','" +agendaVo.getModalidad() + "','"+agendaVo.getTema()+"','" +agendaVo.getConferencista() + "','" +agendaVo.getFecha() + "','" +agendaVo.getHora() + "','" +agendaVo.getLugar() + "')";

        //System.out.println(consulta);
        db.execSQL(consulta);

      //  db.close();
    }

    public void eliminarAgenda(SQLiteDatabase db){
        String consulta="DELETE FROM agenda";
       // System.out.println(consulta);
        db.execSQL(consulta);
        //  db.close();
    }

    public ArrayList<AgendaDiaVo> consultarListaAgenda(SQLiteDatabase db) {
        AgendaDiaVo agendaVo;
        ArrayList<AgendaDiaVo> resultado=new ArrayList<AgendaDiaVo>();

        Cursor cursor = db.rawQuery("SELECT * FROM agenda ", null);

        while (cursor.moveToNext()){
            agendaVo=new AgendaDiaVo();
            agendaVo.setId(cursor.getInt(0));
            agendaVo.setOrden(cursor.getInt(1));
            agendaVo.setModalidad(cursor.getString(2));
            agendaVo.setTema(cursor.getString(3));
            agendaVo.setConferencista(cursor.getString(4));
            agendaVo.setFecha(cursor.getString(5));
            agendaVo.setHora(cursor.getString(6));
            agendaVo.setLugar(cursor.getString(7));
            resultado.add(agendaVo);
        }
        cursor.close();
        return resultado;
    }

}
