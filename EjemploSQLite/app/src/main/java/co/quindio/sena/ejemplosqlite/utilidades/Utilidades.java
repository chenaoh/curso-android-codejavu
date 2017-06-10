package co.quindio.sena.ejemplosqlite.utilidades;

/**
 * Created by CHENAO on 7/05/2017.
 */

public class Utilidades {

    //Constantes campos tabla usuario
    public static final String TABLA_USUARIO="usuario";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_TELEFONO="telefono";

    public static final String CREAR_TABLA_USUARIO="CREATE TABLE " +
            ""+TABLA_USUARIO+" ("+CAMPO_ID+" " +
            "INTEGER, "+CAMPO_NOMBRE+" TEXT,"+CAMPO_TELEFONO+" TEXT)";

}
