package co.quindio.sena.senasoftquindio2016;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import co.quindio.sena.senasoftquindio2016.clases.vo.AgendaVo;
import co.quindio.sena.senasoftquindio2016.clases.vo.CategoriasVo;
import co.quindio.sena.senasoftquindio2016.clases.Constantes;
import co.quindio.sena.senasoftquindio2016.clases.vo.ResultadosVo;
import co.quindio.sena.senasoftquindio2016.clases.vo.TematicaCategoriaVo;
import co.quindio.sena.senasoftquindio2016.dialogos.JornadaCategoriaDialog;
import co.quindio.sena.senasoftquindio2016.dialogos.ResultadosMensajeDiaglog;
import co.quindio.sena.senasoftquindio2016.fragments.AcercaDeFragment;
import co.quindio.sena.senasoftquindio2016.fragments.AgendaDiaFragment;
import co.quindio.sena.senasoftquindio2016.fragments.AgendaFragment;
import co.quindio.sena.senasoftquindio2016.fragments.CategoriasFragment;
import co.quindio.sena.senasoftquindio2016.fragments.ContenedorInfoCreamasFragment;
import co.quindio.sena.senasoftquindio2016.fragments.ContenedorInformacionFragment;
import co.quindio.sena.senasoftquindio2016.fragments.CreaMasFragment;
import co.quindio.sena.senasoftquindio2016.fragments.CreamasQueesFragment;
import co.quindio.sena.senasoftquindio2016.fragments.DescripcionCategoriasFragment;
import co.quindio.sena.senasoftquindio2016.fragments.DetalleCategoriaFragment;
import co.quindio.sena.senasoftquindio2016.fragments.InformacionFragment;
import co.quindio.sena.senasoftquindio2016.fragments.ListaCategoriasFragment;
import co.quindio.sena.senasoftquindio2016.fragments.ListaMuestraComercialFragment;
import co.quindio.sena.senasoftquindio2016.fragments.LugarFragment;
import co.quindio.sena.senasoftquindio2016.fragments.ParticipantesFragment;
import co.quindio.sena.senasoftquindio2016.fragments.ProyectosCreamasFragment;
import co.quindio.sena.senasoftquindio2016.fragments.QueEsFragment;
import co.quindio.sena.senasoftquindio2016.fragments.ResultadosCategoriasFragment;
import co.quindio.sena.senasoftquindio2016.fragments.ResultadosFragment;
import co.quindio.sena.senasoftquindio2016.fragments.RuedaNegociosFragment;
import co.quindio.sena.senasoftquindio2016.interfaces.IComunicaFragments;

public class MainActivity extends AppCompatActivity  implements IComunicaFragments,NavigationView.OnNavigationItemSelectedListener,
        InformacionFragment.OnFragmentInteractionListener,CategoriasFragment.OnFragmentInteractionListener,AgendaFragment.OnFragmentInteractionListener,
        RuedaNegociosFragment.OnFragmentInteractionListener,CreaMasFragment.OnFragmentInteractionListener, AcercaDeFragment.OnFragmentInteractionListener,
        QueEsFragment.OnFragmentInteractionListener,ParticipantesFragment.OnFragmentInteractionListener,LugarFragment.OnFragmentInteractionListener,
        CreamasQueesFragment.OnFragmentInteractionListener,DetalleCategoriaFragment.OnFragmentInteractionListener,ResultadosFragment.OnFragmentInteractionListener,
        ProyectosCreamasFragment.OnFragmentInteractionListener, ListaCategoriasFragment.OnFragmentInteractionListener,DescripcionCategoriasFragment.OnFragmentInteractionListener,
        ContenedorInformacionFragment.OnFragmentInteractionListener, ContenedorInfoCreamasFragment.OnFragmentInteractionListener,ListaMuestraComercialFragment.OnFragmentInteractionListener,
        AgendaDiaFragment.OnFragmentInteractionListener,ResultadosCategoriasFragment.OnFragmentInteractionListener{

    DetalleCategoriaFragment detalleCategoriaFragment;
    Fragment miFragment=null;

    String titulo="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionsMenu fabMenu = (FloatingActionsMenu) findViewById(R.id.fab_accionesMultiples);
        FloatingActionButton fabResultados = (FloatingActionButton) findViewById(R.id.fab_resultados);
        FloatingActionButton fabAgenda = (FloatingActionButton) findViewById(R.id.fab_agenda);
        FloatingActionButton fabMapa = (FloatingActionButton) findViewById(R.id.fab_mapa);

        fabResultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miFragment=new ResultadosFragment();
                setTitle("Resultados");
                Constantes.visualizacion=Constantes.GRID;
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,miFragment).commit();
                mostrarMensajeResultados();
                fabMenu.collapse();
            }
        });

        fabAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miFragment=new AgendaFragment();
                setTitle("Agenda académica");
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,miFragment).commit();
                fabMenu.collapse();
            }
        });

        fabMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
               //         .setAction("Action", null).show();
                Intent intent=new Intent(MainActivity.this,MapaEventoActivity.class);
                startActivity(intent);
                fabMenu.collapse();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
       // setTitle("Bienvenido(a)");
       //
            //asignar en los otros fragments la variable en cero para que omita esta validación.

            if (Constantes.ultimo_layout!=0){
                switch (Constantes.ultimo_layout)
                {
                    case R.id.viewPagerInformacion:
                        //Constantes.titulo=getString(R.string.item_informacion);
                        setTitle(Constantes.titulo);
                       // miFragment=new InformacionFragment();
                        miFragment=new ContenedorInformacionFragment();
                        break;
                    case R.id.viewPagerCreaMas:
                          setTitle(Constantes.titulo);
                      //  miFragment=new CreaMasFragment();
                        miFragment = new ContenedorInfoCreamasFragment();
                        break;
                    case R.layout.fragment_categorias:
                        setTitle(Constantes.titulo);
                        miFragment=new CategoriasFragment();
                        break;
                    case R.layout.fragment_acerca_de:
                        setTitle(Constantes.titulo);
                        miFragment=new AcercaDeFragment();
                        break;
                }
            }

        if (miFragment!=null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,miFragment).commit();
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        Fragment miFragment=null;
        Boolean fragmentSeleccionado=false;

        //if (id == R.id.nav_inicio) {
         //  miFragment=new BienvenidaFragment();
         //  setTitle("Bienvenido(a)");
         // fragmentSeleccionado=true;
       // } else
        if (id == R.id.nav_informacion) {
            //miFragment=new InformacionFragment();
            Constantes.titulo=getString(R.string.item_informacion);
            miFragment=new ContenedorInformacionFragment();
            setTitle(Constantes.titulo);
            Constantes.ultimo_layout_panel_info_pos=0;
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_agenda) {
            miFragment=new AgendaFragment();
            Constantes.titulo="Agenda académica";
            setTitle(Constantes.titulo);
            fragmentSeleccionado=true;
            Constantes.consultaAgenda=0;
        } else if (id == R.id.nav_categorias) {
            miFragment=new CategoriasFragment();
            Constantes.titulo="Categorias";
            setTitle(Constantes.titulo);
            Constantes.visualizacion=Constantes.LIST;
            fragmentSeleccionado=true;
        }
        else if (id == R.id.nav_resultados) {
            miFragment=new ResultadosFragment();
            Constantes.titulo="Resultados";
            setTitle(Constantes.titulo);
            Constantes.visualizacion=Constantes.GRID;
            fragmentSeleccionado=true;
            mostrarMensajeResultados();
        }
        else if (id == R.id.nav_rueda) {
            miFragment=new ContenedorInfoCreamasFragment();
            Constantes.titulo="Rueda de Proyección Empresarial";
            setTitle(Constantes.titulo);
            fragmentSeleccionado=true;
            Constantes.ultimo_layout_panel_creamas_pos=0;
            Constantes.rotacionProyectosCreamas=0;
            Constantes.rotacion=0;
        } else if (id == R.id.nav_proyectos) {
//            miFragment=new MuestraComercialFragment();
            miFragment=new ListaMuestraComercialFragment();
            Constantes.titulo="Empresas";
            setTitle(Constantes.titulo);
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_creamas) {
            //miFragment=new CreaMasFragment();
            miFragment = new RuedaNegociosFragment();
           // miFragment = new CreamasQueesFragment();
            Constantes.titulo="CREAMÁS";
            setTitle(Constantes.titulo);
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_acerca) {
            miFragment=new AcercaDeFragment();
            Constantes.titulo="Acerca de...";
            setTitle(Constantes.titulo);
            fragmentSeleccionado=true;
          //  Intent intent=new Intent(MainActivity.this,MapaActivity.class);
          //  startActivity(intent);
        }

        if (fragmentSeleccionado==true){
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,miFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /*se controla la pulsacion del boton atras*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Desea salir de la aplicación?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            builder.show();
        }

        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    public void mostrarMensajeResultados(){
        ResultadosMensajeDiaglog dialogoResultados=new ResultadosMensajeDiaglog();
        dialogoResultados.show(getSupportFragmentManager(),"DialogoJornadaCategoria");
    }


    @Override
    public void enviaAgenda(AgendaVo agendaVo) {
     //   Toast.makeText(this, "Agenda - Dia: "+agendaVo.getDia(), Toast.LENGTH_SHORT).show();
      //
        AgendaDiaFragment agendaDiaFragment=new AgendaDiaFragment();

        Bundle bundleEnvio=new Bundle();
        bundleEnvio.putSerializable("agenda", agendaVo);

       // dialogoAgenda.setArguments(bundleEnvio);
       // dialogoAgenda.show(getSupportFragmentManager(),"DialogoAgenda");
        agendaDiaFragment.setArguments(bundleEnvio);
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,agendaDiaFragment).commit();

    }


    @Override
    public void enviaTematicaCategoria(TematicaCategoriaVo tematicaCategoriaVo) {
        JornadaCategoriaDialog dialogoJornada=new JornadaCategoriaDialog();

        Bundle bundleEnvio=new Bundle();
        bundleEnvio.putSerializable("tematica", tematicaCategoriaVo);

        dialogoJornada.setArguments(bundleEnvio);
        dialogoJornada.show(getSupportFragmentManager(),"DialogoJornadaCategoria");

    }


    @Override
    public void enviaCategoriaResultado(ResultadosVo resultadosVo) {
        ResultadosCategoriasFragment resultadosCategoriasFragment= new ResultadosCategoriasFragment();
        Bundle bundleEnvio=new Bundle();
        bundleEnvio.putSerializable("resultados", resultadosVo);
        resultadosCategoriasFragment.setArguments(bundleEnvio);

        //cargamos el fragment en el activity
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedorFragments,resultadosCategoriasFragment).addToBackStack(null).commit();
    }

    @Override
    public void enviaCategoria(CategoriasVo categoriaVo) {

        int layout;
        detalleCategoriaFragment=new DetalleCategoriaFragment();
        Bundle bundleEnvio=new Bundle();
        bundleEnvio.putSerializable("categoria", categoriaVo);
        detalleCategoriaFragment.setArguments(bundleEnvio);

        if (findViewById(R.id.contenedorCategorias)!=null){
            layout=R.id.contenedorCategorias;
        }else{
            layout=R.id.fragContenido;
        }
        //cargamos el fragment en el activity
        getSupportFragmentManager().beginTransaction()
                .replace(layout,detalleCategoriaFragment).addToBackStack(null).commit();//permite actualizar y no sobreescribir

    }

    public void descargaLineamientos(View view) {
        if (view.getId()==R.id.btnLineamientos){
            String url="";
            switch (Constantes.seleccionLineamiento){
                case 1: url="http://senasoft.sena.edu.co/archivos/algoritmia.pdf";
                    break;
                case 2: url="http://senasoft.sena.edu.co/archivos/animacion_3d.pdf";
                    break;
                case 3: url="http://senasoft.sena.edu.co/archivos/aplicaciones_net.pdf";
                    break;
                case 4: url="http://senasoft.sena.edu.co/archivos/aplicaciones_moviles_android.pdf";
                    break;
                case 5: url="http://senasoft.sena.edu.co/archivos/aplicaciones_web_php.pdf";
                    break;
                case 6: url="http://senasoft.sena.edu.co/archivos/bases_datos.pdf";
                    break;
                case 7: url="http://senasoft.sena.edu.co/archivos/diseno_orientado_objetos.pdf";
                    break;
                case 8: url="http://senasoft.sena.edu.co/archivos/instalacion_hardening_sistemas_operativos.pdf";
                    break;
                case 9: url="http://senasoft.sena.edu.co/archivos/java_web.pdf";
                    break;
                case 10: url="http://senasoft.sena.edu.co/archivos/produccion_medios_audiovisuales.pdf";
                    break;
                case 11: url="http://senasoft.sena.edu.co/archivos/produccion_multimedia.pdf";
                    break;
                case 12: url="http://senasoft.sena.edu.co/archivos/proyectos_agiles_scrum.pdf";
                    break;
                case 13: url="http://senasoft.sena.edu.co/archivos/redes_datos.pdf";
                    break;
                case 14: url="http://senasoft.sena.edu.co/archivos/sistemas_operativos_red.pdf";
                    break;
                case 15: url="http://senasoft.sena.edu.co/archivos/videojuegos.pdf";
                    break;
                case 16: url="http://senasoft.sena.edu.co/archivos/lineamientos.pdf";
                    break;
                default: url="vacio";
            }

            if (!url.equals("vacio")){
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }else{
                Toast.makeText(this, "No se puede obtener el lineamiento, intente mas tarde", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
