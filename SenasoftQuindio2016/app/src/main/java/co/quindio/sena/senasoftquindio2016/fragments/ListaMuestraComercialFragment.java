package co.quindio.sena.senasoftquindio2016.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.os.Handler;
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

import co.quindio.sena.senasoftquindio2016.MainActivity;
import co.quindio.sena.senasoftquindio2016.R;
import co.quindio.sena.senasoftquindio2016.adapters.EmpresasAdapter;
import co.quindio.sena.senasoftquindio2016.bd.ConexionSQLiteHelper;
import co.quindio.sena.senasoftquindio2016.clases.Constantes;
import co.quindio.sena.senasoftquindio2016.clases.dao.EmpresaDao;
import co.quindio.sena.senasoftquindio2016.clases.vo.EmpresaVo;
import co.quindio.sena.senasoftquindio2016.interfaces.IComunicaFragments;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListaMuestraComercialFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListaMuestraComercialFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaMuestraComercialFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    ImageView imagenSinConexion;
    Activity actividad;
    View vista;
    RecyclerView recyclerEmpresas;
    ArrayList<EmpresaVo> listaEmpresas;
    private ProgressBar mProgressView;
    private TextView textProgress;

    private TextView txtInfo;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    SQLiteDatabase bd;
    int finalizaSensor=0;



    IComunicaFragments interfaceComunicacionFragments;
    private EmpresasAdapter miAdapter;

    int ingreso=0;
    private int prevOrientation;

    public ListaMuestraComercialFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaMuestraComercialFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaMuestraComercialFragment newInstance(String param1, String param2) {
        ListaMuestraComercialFragment fragment = new ListaMuestraComercialFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Constantes.ultimo_layout=0;

        vista=  inflater.inflate(R.layout.fragment_lista_muestra_comercial, container, false);
        recyclerEmpresas = (RecyclerView)vista.findViewById(R.id.list_card_recycler);
        txtInfo= (TextView) vista.findViewById(R.id.txtinfo);

        imagenSinConexion= (ImageView) vista.findViewById(R.id.imagenSinConexion);
        imagenSinConexion.setVisibility(View.INVISIBLE);
       //
        textProgress= (TextView) vista.findViewById(R.id.textProgress);
        mProgressView = (ProgressBar) vista.findViewById(R.id.progress);
        mProgressView.getIndeterminateDrawable().setColorFilter(Color.rgb(0,149,127), PorterDuff.Mode.MULTIPLY);
        //Color.rgb(0,149,127)   - Color.rgb(122,181,29)
        showProgress(true);
        textProgress.setVisibility(View.VISIBLE);

        prevOrientation = actividad.getRequestedOrientation();


        listaEmpresas =new ArrayList<EmpresaVo>();
        request= Volley.newRequestQueue(this.actividad);
        ConnectivityManager connMgr = (ConnectivityManager) actividad.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
                //actividad.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                actividad.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            } else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                actividad.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            } else {
                actividad.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
            }

                cargarWebService(getString(R.string.ip));
        } else {
            txtInfo.setText("No se pudo conectar, verifique el acceso a Internet e Intente mas tarde");
            obtenerListaEmpresas();
        }

        if (vista.findViewById(R.id.muestraLand)==null
                && vista.findViewById(R.id.muestraPort)==null && vista.findViewById(R.id.muestraLand10)==null ){
            recyclerEmpresas.setLayoutManager(new LinearLayoutManager(this.actividad));
        }else{
            if (vista.findViewById(R.id.muestraPort)!=null){
                recyclerEmpresas.setLayoutManager(new GridLayoutManager(this.actividad,2));
            }else{
                if (vista.findViewById(R.id.muestraLand)!=null){
                    recyclerEmpresas.setLayoutManager(new GridLayoutManager(this.actividad,3));
                }else{
                    if (vista.findViewById(R.id.muestraLand10)!=null){
                        recyclerEmpresas.setLayoutManager(new GridLayoutManager(this.actividad,4));
                    }
                }
            }
        }

        recyclerEmpresas.setHasFixedSize(true);

        return vista;
    }


    private void cargarWebService(String server) {
        String url=server+"/senasoftws/wsJSONConsultarListaEmpresas.php";
        System.out.println("SERVER: "+url);
        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // Toast.makeText(actividad, "Conectados: "+response, Toast.LENGTH_LONG).show();

                try {
                    JSONArray jsonArray = response.optJSONArray("empresa");
                    EmpresaVo empresaVo;
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject =  jsonArray.getJSONObject(i);
                        empresaVo= new EmpresaVo();
                        empresaVo.setId(jsonObject.optInt("empresa_id"));
                        empresaVo.setNombreEmpresa(jsonObject.optString("nombre"));
                        empresaVo.setDescripcion(jsonObject.optString("descripcion"));
                        empresaVo.setData(jsonObject.optString("logo"));
                        listaEmpresas.add(empresaVo);
                    }
                    registrarEmpresasBd();
                    obtenerListaEmpresas();
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
            //    System.out.println("ERROR VOLEY: "+volleyError.getMessage());
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
                obtenerListaEmpresas();
            }
        }
    }

    private void registrarEmpresasBd() {
        bd=conectarBD();
        EmpresaVo empresaVo;
        EmpresaDao empresaDao=new EmpresaDao();
        empresaDao.eliminarEmpresa(bd);//Limpiamos la agenda

        for (int i =0; i<listaEmpresas.size();i++){
              empresaVo=listaEmpresas.get(i);
              empresaDao.insertarEmpresa(empresaVo,bd);
        }

        bd.close();
    }

    private void consultarEmpresas() {
        listaEmpresas.clear();
        bd=conectarBD();
        EmpresaDao empresaDao=new EmpresaDao();
        //  List<String> listaP=new ArrayList<String>();;
        ArrayList<EmpresaVo> empresaList=empresaDao.consultarListaEmpresas(bd);

        for (int i=0;i<empresaList.size();i++){
            EmpresaVo empresaVo=empresaList.get(i);
            listaEmpresas.add(empresaVo);
        }
        bd.close();
    }

    public SQLiteDatabase conectarBD() {
        ConexionSQLiteHelper conexionBD=new ConexionSQLiteHelper(this.actividad,Constantes.BD_NAME,null,1);
        return conexionBD.getWritableDatabase();
    }

    private void obtenerListaEmpresas() {
        consultarEmpresas();

        if (listaEmpresas.size()>0){
            imagenSinConexion.setVisibility(View.INVISIBLE);
        }else{
            imagenSinConexion.setVisibility(View.VISIBLE);
        }
        showProgress(false);
        textProgress.setVisibility(View.INVISIBLE);
        miAdapter=new EmpresasAdapter(listaEmpresas);
        recyclerEmpresas.setAdapter(miAdapter);


        actividad.setRequestedOrientation(prevOrientation);

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
            interfaceComunicacionFragments=(IComunicaFragments) this.actividad;
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
