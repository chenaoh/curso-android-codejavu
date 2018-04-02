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
import android.widget.ImageView;
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
import co.quindio.sena.senasoftquindio2016.adapters.CreamasProjectAdapter;
import co.quindio.sena.senasoftquindio2016.bd.ConexionSQLiteHelper;
import co.quindio.sena.senasoftquindio2016.clases.Constantes;
import co.quindio.sena.senasoftquindio2016.clases.dao.ProyectoCreamasDao;
import co.quindio.sena.senasoftquindio2016.clases.vo.AgendaDiaVo;
import co.quindio.sena.senasoftquindio2016.clases.vo.CategoriasVo;
import co.quindio.sena.senasoftquindio2016.clases.vo.ProyectoCreamasVo;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProyectosCreamasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProyectosCreamasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProyectosCreamasFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    ImageView imagenSinConexion;
    Activity actividad;
    View vista;
    RecyclerView recyclerProyectos;
    ArrayList<ProyectoCreamasVo> listaProyectos;

    private ProgressBar mProgressView;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    SQLiteDatabase bd;
    private TextView txtInfo;
    private TextView textProgress;

    CreamasProjectAdapter miAdapter;
    int ingreso=0;
    private int prevOrientation;

//    IComunicaFragments interfaceComunicacionFragments;


    public ProyectosCreamasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProyectosCreamasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProyectosCreamasFragment newInstance(String param1, String param2) {
        ProyectosCreamasFragment fragment = new ProyectosCreamasFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        vista=  inflater.inflate(R.layout.fragment_proyectos_creamas, container, false);
        recyclerProyectos = (RecyclerView)vista.findViewById(R.id.list_card_projects);
        txtInfo= (TextView) vista.findViewById(R.id.txtinfo);
        textProgress= (TextView) vista.findViewById(R.id.textProgress);
        imagenSinConexion= (ImageView) vista.findViewById(R.id.imagenSinConexion);
        imagenSinConexion.setVisibility(View.INVISIBLE);

        if (vista.findViewById(R.id.proyectosCreamas10)==null){
            //recyclerProyectos.setLayoutManager(new LinearLayoutManager(this.actividad));
            recyclerProyectos.setLayoutManager(new GridLayoutManager(this.actividad,2));
        }else{
            recyclerProyectos.setLayoutManager(new GridLayoutManager(this.actividad,3));
        }

        recyclerProyectos.setHasFixedSize(true);


        mProgressView = (ProgressBar) vista.findViewById(R.id.progress);
        mProgressView.getIndeterminateDrawable().setColorFilter(Color.rgb(0,149,127), PorterDuff.Mode.MULTIPLY);
        //Color.rgb(0,149,127)   - Color.rgb(122,181,29)
        showProgress(true);
        textProgress.setVisibility(View.VISIBLE);
        listaProyectos =new ArrayList<ProyectoCreamasVo>();

        prevOrientation = actividad.getRequestedOrientation();

        request= Volley.newRequestQueue(this.actividad);
        ConnectivityManager connMgr = (ConnectivityManager) actividad.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            if (Constantes.rotacionProyectosCreamas==0){
                Constantes.rotacionProyectosCreamas=1;

                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    actividad.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                } else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    actividad.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                } else {
                    actividad.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
                }

                cargarWebService(getString(R.string.ip));
            }else{
                obtenerListaProyectos();
            }

        } else {
            txtInfo.setText("No se pudo conectar, verifique el acceso a Internet e Intente nuevamente");
            obtenerListaProyectos();
        }

        return vista;
    }

    private void cargarWebService(String server) {
        String url=server+"/senasoftws/wsJSONConsultarListaProyectos.php";
        System.out.println("SERVER: "+url);
        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // Toast.makeText(actividad, "Conectados: "+response, Toast.LENGTH_LONG).show();

                try {
                    JSONArray jsonArray = response.optJSONArray("proyecto");
                    ProyectoCreamasVo proyectoCreamasVo;
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject =  jsonArray.getJSONObject(i);
                        proyectoCreamasVo= new ProyectoCreamasVo();
                        proyectoCreamasVo.setId(jsonObject.optInt("proyecto_id"));
                        proyectoCreamasVo.setNombreProyecto(jsonObject.optString("nombre"));
                        proyectoCreamasVo.setDescripcion(jsonObject.optString("descripcion"));
                        proyectoCreamasVo.setData(jsonObject.optString("logo"));
                        listaProyectos.add(proyectoCreamasVo);
                    }
                    registrarProyectosBd();
                    obtenerListaProyectos();
                    txtInfo.setText("");
                } catch (JSONException e) {
                    e.printStackTrace();
                    realizarIntento();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                // Toast.makeText(actividad, "No se pudo establecer conexión con el servidor: "+volleyError, Toast.LENGTH_LONG).show();
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
                obtenerListaProyectos();
            }
        }
    }

    private void registrarProyectosBd() {
        bd=conectarBD();
        ProyectoCreamasVo proyectoCreamasVo;
        ProyectoCreamasDao proyectoCreamasDao=new ProyectoCreamasDao();
        proyectoCreamasDao.eliminarProyecto(bd);//Limpiamos el proyecto

        for (int i =0; i<listaProyectos.size();i++){
             proyectoCreamasVo=listaProyectos.get(i);
             proyectoCreamasDao.insertarProyecto(proyectoCreamasVo,bd);
        }

        bd.close();
    }

    private void consultarProyectos() {
        listaProyectos.clear();
        bd=conectarBD();
        ProyectoCreamasDao proyectoCreamasDao=new ProyectoCreamasDao();
        //  List<String> listaP=new ArrayList<String>();;
        ArrayList<ProyectoCreamasVo> ProyectosList=proyectoCreamasDao.consultarListaProyecto(bd);

        for (int i=0;i<ProyectosList.size();i++){
            ProyectoCreamasVo proyectoCreamasVo=ProyectosList.get(i);
            listaProyectos.add(proyectoCreamasVo);
        }
    }

    private void obtenerListaProyectos() {
        consultarProyectos();

        if (listaProyectos.size()>0){
            imagenSinConexion.setVisibility(View.INVISIBLE);
            miAdapter=new CreamasProjectAdapter(listaProyectos);
            recyclerProyectos.setAdapter(miAdapter);
         //   Bundle bundleEnvio=new Bundle();
       //     bundleEnvio.putSerializable("lista", listaProyectos);
       //     this.setArguments(bundleEnvio);
        }else{
            imagenSinConexion.setVisibility(View.VISIBLE);
        }

        showProgress(false);
        textProgress.setVisibility(View.INVISIBLE);
        actividad.setRequestedOrientation(prevOrientation);
    }

    public SQLiteDatabase conectarBD() {
        ConexionSQLiteHelper conexionBD=new ConexionSQLiteHelper(this.actividad, Constantes.BD_NAME,null,1);
        return conexionBD.getWritableDatabase();
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
//            interfaceComunicacionFragments=(IComunicaFragments) this.actividad;
        }
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
