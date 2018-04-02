package co.quindio.sena.senasoftquindio2016.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import co.quindio.sena.senasoftquindio2016.clases.Constantes;

public class ConexionSQLiteHelper extends SQLiteOpenHelper
{

    public ConexionSQLiteHelper(Context context, String nombreBd,
                                SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombreBd, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(Constantes.CREAR_TABLA_AGENDA);
        db.execSQL(Constantes.CREAR_TABLA_EMPRESA);
        db.execSQL(Constantes.CREAR_TABLA_PROYECTO_CREAMAS);
        db.execSQL(Constantes.CREAR_TABLA_RESULTADOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        //Se crea la nueva versión de la tabla
        db.execSQL(Constantes.CREAR_TABLA_AGENDA);
        db.execSQL(Constantes.CREAR_TABLA_EMPRESA);
        db.execSQL(Constantes.CREAR_TABLA_PROYECTO_CREAMAS);
        db.execSQL(Constantes.CREAR_TABLA_RESULTADOS);
    }
}

