package co.quindio.sena.senasoftquindio2016.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import co.quindio.sena.senasoftquindio2016.R;
import co.quindio.sena.senasoftquindio2016.adapters.AgendaAdapter;
import co.quindio.sena.senasoftquindio2016.bd.ConexionSQLiteHelper;
import co.quindio.sena.senasoftquindio2016.clases.dao.AgendaDiaDao;
import co.quindio.sena.senasoftquindio2016.clases.vo.AgendaDiaVo;
import co.quindio.sena.senasoftquindio2016.clases.vo.AgendaVo;
import co.quindio.sena.senasoftquindio2016.clases.Constantes;
import co.quindio.sena.senasoftquindio2016.interfaces.IComunicaFragments;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AgendaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AgendaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgendaFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    IComunicaFragments interfaceComunicaFragment;

    Activity actividad;
    View vista;
    RecyclerView recyclerAgenda;
    ArrayList<AgendaVo> listaAgenda;
    ArrayList<AgendaDiaVo> listaAgendaCompleta;
    private ProgressBar mProgressView;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    AgendaAdapter miAdapter;
    TextView txtInfo;
    boolean isRemote=false;

    SQLiteDatabase bd;
    int ingreso=0;
    private int prevOrientation;

    public AgendaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgendaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AgendaFragment newInstance(String param1, String param2) {
        AgendaFragment fragment = new AgendaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Constantes.ultimo_layout=0;
        vista=  inflater.inflate(R.layout.fragment_agenda, container, false);
        recyclerAgenda = (RecyclerView)vista.findViewById(R.id.list_card_recycler_agenda);
        txtInfo= (TextView) vista.findViewById(R.id.txtinfo);
        listaAgendaCompleta=new ArrayList<>();

        mProgressView = (ProgressBar) vista.findViewById(R.id.progress);
        mProgressView.getIndeterminateDrawable().setColorFilter(Color.rgb(0,149,127), PorterDuff.Mode.MULTIPLY);
        //Color.rgb(0,149,127)   - Color.rgb(122,181,29)
        showProgress(true);

        request= Volley.newRequestQueue(this.actividad);
        prevOrientation = actividad.getRequestedOrientation();

        ConnectivityManager connMgr = (ConnectivityManager) actividad.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            if (Constantes.consultaAgenda==0){


                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    actividad.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                } else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    actividad.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                } else {
                    actividad.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
                }


                cargarWebService(getString(R.string.ip));
                Constantes.consultaAgenda=1;
            }else{
                obtenerListaAgenda();
            }

        } else {
            txtInfo.setText("No se pudo conectar, verifique el acceso a Internet e Intente nuevamente");
            obtenerListaAgenda();
        }


        if (vista.findViewById(R.id.agendaLand)==null){
            recyclerAgenda.setLayoutManager(new LinearLayoutManager(this.actividad));
        }else{
            recyclerAgenda.setLayoutManager(new GridLayoutManager(this.actividad,2));
        }


        recyclerAgenda.setHasFixedSize(true);

        return vista;
    }

    public SQLiteDatabase conectarBD() {
        ConexionSQLiteHelper conexionBD=new ConexionSQLiteHelper(this.actividad,Constantes.BD_NAME,null,1);
        return conexionBD.getWritableDatabase();
    }

    private void cargarWebService(String server) {
        String url=server+"/senasoftws/wsJSONConsultarLista.php";
        System.out.println("SERVER: "+url);
        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.optJSONArray("agenda");
                    AgendaDiaVo agendaDia;
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject =  jsonArray.getJSONObject(i);
                        agendaDia=new AgendaDiaVo();
                        agendaDia.setId(jsonObject.getInt("agenda_id"));
                        agendaDia.setOrden(jsonObject.getInt("orden"));
                        agendaDia.setModalidad(jsonObject.getString("modalidad"));
                        agendaDia.setTema(jsonObject.getString("tema"));
                        agendaDia.setConferencista(jsonObject.getString("conferencista"));
                        agendaDia.setFecha(jsonObject.getString("fecha"));
                        agendaDia.setHora(jsonObject.getString("hora"));
                        agendaDia.setLugar(jsonObject.getString("lugar"));
                        listaAgendaCompleta.add(agendaDia);
                    }
                    registrarAgendaBd();
                    obtenerListaAgenda();
                    txtInfo.setText("");
                } catch (JSONException e) {
                    e.printStackTrace();
                    realizarIntento();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                realizarIntento();
            }
        });
        request.add(jsonObjectRequest);
    }


    private void realizarIntento() {
        if (ingreso==0){
            // System.out.println("WEB SERVICE ALTERNO 1");
            cargarWebService(getString(R.string.ip2));
            ingreso=1;
        }else{
            if (ingreso==1){
                //   System.out.println("WEB SERVICE ALTERNO 2");
                cargarWebService(getString(R.string.ip3));
                ingreso=2;
            }else{
                //  System.out.println("CONEXION LOCAL");
                txtInfo.setText("No se pudo establecer conexión con el servidor, Intente mas tarde");
                obtenerListaAgenda();
            }
        }
    }

    private void obtenerListaAgenda() {

        //Instanciamos la lista de Versiones
        listaAgenda =new ArrayList<AgendaVo>();

        //Alimentamos la lista con objetos de tipo Versiones usando el constructor Explicito
        listaAgenda.add(new AgendaVo(1,"Día 1 - Lunes 24","Llegada de Aprendices e Instructores\nRegistro de Equipos",R.drawable.lunes,obtenerListaAgendaDia(1)));
        listaAgenda.add(new AgendaVo(2,"Día 2 - Martes 25","Acto de inauguración\nCompetencias FASE |\nAgenda académica\nActividades del evento\nRueda de proyección Empresarial CREAMÁS",R.drawable.martes,obtenerListaAgendaDia(2)));
        listaAgenda.add(new AgendaVo(3,"Día 3 - Miércoles 26","Competencias FASE ||\nAgenda académica\nActividades del evento\nRueda de proyección Empresarial CREAMÁS",R.drawable.miercoles,obtenerListaAgendaDia(3)));
        listaAgenda.add(new AgendaVo(4,"Día 4 - Jueves 27","Competencias FASE |||\nAgenda académica\nActividades del evento\nRueda de proyección Empresarial CREAMÁS\nClausura y premiación",R.drawable.jueves,obtenerListaAgendaDia(4)));
        listaAgenda.add(new AgendaVo(5,"Día 5 - Viernes 28","Regreso a ciudades de origen",R.drawable.viernes,obtenerListaAgendaDia(5)));
        showProgress(false);
        miAdapter=new AgendaAdapter(listaAgenda);
        recyclerAgenda.setAdapter(miAdapter);

        miAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   Toast.makeText(actividad, "Seleccion Dia " +listaAgenda.get(recyclerAgenda.getChildAdapterPosition(v)).getId(), Toast.LENGTH_SHORT).show();
                interfaceComunicaFragment.enviaAgenda(listaAgenda.get(recyclerAgenda.getChildAdapterPosition(v)));

            }
        });

        actividad.setRequestedOrientation(prevOrientation);

    }

    private void registrarAgendaBd() {
        bd=conectarBD();
        AgendaDiaVo agendaVo;
        ArrayList<AgendaDiaVo> listaAgendaDia=new ArrayList<>();
        AgendaDiaDao agendaDiaDao=new AgendaDiaDao();
        agendaDiaDao.eliminarAgenda(bd);//Limpiamos la agenda

        for (int x =1; x<=5;x++){//5 representa la cantidad de dias
            for (int i =0; i<listaAgendaCompleta.size();i++){
                if (listaAgendaCompleta.get(i).getId()==x){
                    agendaVo=listaAgendaCompleta.get(i);
                    agendaDiaDao.insertarAgenda(agendaVo,bd);
                }
            }
        }
        bd.close();
    }

    private void consultarAgenda() {
        listaAgendaCompleta.clear();
        bd=conectarBD();
        AgendaDiaDao agendaDiaDao=new AgendaDiaDao();
        //  List<String> listaP=new ArrayList<String>();;
        ArrayList<AgendaDiaVo> listaAgendaDia=agendaDiaDao.consultarListaAgenda(bd);

        for (int i=0;i<listaAgendaDia.size();i++){
            AgendaDiaVo miAgenda=listaAgendaDia.get(i);
            listaAgendaCompleta.add(miAgenda);
        }
        bd.close();
    }

    private ArrayList<AgendaDiaVo> obtenerListaAgendaDia(int dia) {

        consultarAgenda();
        ArrayList<AgendaDiaVo> listaAgendaDia=new ArrayList<>();

        if (listaAgendaCompleta.size()>0){
            for (int i =0; i<listaAgendaCompleta.size();i++){
                if (listaAgendaCompleta.get(i).getId()==dia){
                    listaAgendaDia.add(listaAgendaCompleta.get(i));
                }
            }
        }

        return listaAgendaDia;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            this.actividad=(Activity) context;
            interfaceComunicaFragment=(IComunicaFragments) this.actividad;
        }
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
