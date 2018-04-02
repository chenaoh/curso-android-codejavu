package co.quindio.sena.senasoftquindio2016.clases;

import co.quindio.sena.senasoftquindio2016.R;

/**
 * Created by CHENAO on 7/08/2016.
 */
public class Constantes {

    /**Constantes BD*/
    public static final String BD_NAME = "senasoft_bd";
    public static final String CREAR_TABLA_AGENDA = "CREATE TABLE agenda (agenda_id INTEGER, orden INTEGER, modalidad TEXT, tema TEXT, conferencista TEXT, fecha TEXT, hora TEXT, lugar TEXT)";
    public static final String CREAR_TABLA_EMPRESA = "CREATE TABLE empresa (empresa_id INTEGER, nombre TEXT, logo BLOB)";
    public static final String CREAR_TABLA_PROYECTO_CREAMAS = "CREATE TABLE proyecto_creamas (proyecto_id INTEGER, nombre TEXT, descripcion TEXT, logo BLOB)";
    public static final String CREAR_TABLA_RESULTADOS = "CREATE TABLE resultados (categoria_id TEXT, regional TEXT, centro TEXT, jornada1 REAL,jornada2 REAL,jornada3 REAL,total REAL)";

    public static String titulo="SENASOFT 2016";

    public static final int LIST=1;
    public static final int GRID=2;

    public static boolean PORTRAIT=true;

    public static int rotacionInformacion=0;
    public static int rotacionInfoCreamas=0;
    public static int rotacionMuestraComercial=0;
    public static int rotacion=0;

    public static int consultaAgenda=0;
    public static int seleccionLineamiento=0;

    public static int visualizacion=LIST;

    public static int rotacionProyectosCreamas=0;

    public static int ultimo_layout= R.id.viewPagerInformacion;
    public static int ultimo_layout_panel_info_pos=0;
    public static int ultimo_layout_panel_creamas_pos=0;

}
