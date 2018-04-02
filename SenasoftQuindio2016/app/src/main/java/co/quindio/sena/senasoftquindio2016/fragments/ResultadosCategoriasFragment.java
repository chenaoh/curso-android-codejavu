package co.quindio.sena.senasoftquindio2016.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import co.quindio.sena.senasoftquindio2016.adapters.ResultadosCategoriasAdapter;
import co.quindio.sena.senasoftquindio2016.bd.ConexionSQLiteHelper;
import co.quindio.sena.senasoftquindio2016.clases.Constantes;
import co.quindio.sena.senasoftquindio2016.clases.dao.AgendaDiaDao;
import co.quindio.sena.senasoftquindio2016.clases.dao.ResultadosDao;
import co.quindio.sena.senasoftquindio2016.clases.vo.AgendaDiaVo;
import co.quindio.sena.senasoftquindio2016.clases.vo.CategoriasVo;
import co.quindio.sena.senasoftquindio2016.clases.vo.ResultadosCategoriaVo;
import co.quindio.sena.senasoftquindio2016.clases.vo.ResultadosVo;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ResultadosCategoriasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ResultadosCategoriasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultadosCategoriasFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    Activity actividad;
    View vista;
    RecyclerView recyclerCategorias;
    ArrayList<ResultadosCategoriaVo> listaResultadosCategorias;
    private ProgressBar mProgressView;
    TextView nombreCategoria, txtinfo;
    ImageView logoCategoria;
    LinearLayout layoutCategoria;
    LinearLayout layoutValidacion;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    SQLiteDatabase bd;
    int ingreso=0,resultadoId=0;

    ResultadosCategoriasAdapter miAdapter;

    public ResultadosCategoriasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultadosCategoriasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultadosCategoriasFragment newInstance(String param1, String param2) {
        ResultadosCategoriasFragment fragment = new ResultadosCategoriasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista =inflater.inflate(R.layout.fragment_resultados_categorias, container, false);


        Bundle objetoEnviadoBundle=getArguments();
        ResultadosVo resultadosVo=null;
        if (objetoEnviadoBundle!=null){
            resultadosVo= (ResultadosVo) objetoEnviadoBundle.getSerializable("resultados");
            resultadoId=resultadosVo.getId();
        }

        logoCategoria= (ImageView) vista.findViewById(R.id.imgCategoria);
        nombreCategoria= (TextView) vista.findViewById(R.id.textViewCategoria);
        txtinfo= (TextView) vista.findViewById(R.id.txtinfo);
        layoutCategoria= (LinearLayout) vista.findViewById(R.id.layoutId);
        layoutValidacion= (LinearLayout) vista.findViewById(R.id.layoutValidacion);

        nombreCategoria.setText(resultadosVo.getNombreCategoria());
        logoCategoria.setImageResource(resultadosVo.getIconoId());
        layoutCategoria.setBackgroundColor(getResources().getColor(resultadosVo.getColorLogo()));
        txtinfo.setBackgroundColor(getResources().getColor(resultadosVo.getColorLogo()));


        recyclerCategorias = (RecyclerView)vista.findViewById(R.id.list_card_resultados_categorias);

       // obtenerListaResultados();
        listaResultadosCategorias=new ArrayList<>();

        mProgressView = (ProgressBar) vista.findViewById(R.id.progress);
        mProgressView.getIndeterminateDrawable().setColorFilter(Color.rgb(0,149,127), PorterDuff.Mode.MULTIPLY);
        //Color.rgb(0,149,127)   - Color.rgb(122,181,29)
        showProgress(true);

        request= Volley.newRequestQueue(this.actividad);


        ConnectivityManager connMgr = (ConnectivityManager) actividad.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            if (resultadoId!=16){
                cargarWebService(resultadoId,getString(R.string.ip));
                txtinfo.setText("");
                layoutValidacion.setVisibility(View.INVISIBLE);
            }else{
                layoutValidacion.setVisibility(View.VISIBLE);
                showProgress(false);
            }


        } else {
            txtinfo.setText("No se pudo conectar, verifique el acceso a Internet e Intente nuevamente");
            obtenerListaResultados(resultadosVo.getId()+"");
        }


        if (vista.findViewById(R.id.resultadosCategoriaPort10)==null
                && vista.findViewById(R.id.resultadosCategoriaLand10)==null){
             recyclerCategorias.setLayoutManager(new LinearLayoutManager(this.actividad));
        }else{
            if (vista.findViewById(R.id.resultadosCategoriaPort10)!=null){
                recyclerCategorias.setLayoutManager(new GridLayoutManager(this.actividad,2));
            }else{
                if (vista.findViewById(R.id.resultadosCategoriaLand10)!=null){
                    recyclerCategorias.setLayoutManager(new GridLayoutManager(this.actividad,3));
                }
            }
        }

        recyclerCategorias.setHasFixedSize(true);

        if (vista.findViewById(R.id.btnAtras)!=null){
            ImageButton btnAtras = (ImageButton) vista.findViewById(R.id.btnAtras);
            btnAtras.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment miFragment=null;
                    miFragment=new ResultadosFragment();
                    actividad.setTitle("Resultados");
                    getFragmentManager().beginTransaction().replace(R.id.contenedorFragments,miFragment).commit();
                }
            });
        }

        return vista;
    }

    private void validarColorLayout(String colorLogo) {

    }


    private void cargarWebService(int id, String server) {
        final String idResultado=id+"";
        String url=server+"/senasoftws/wsJSONConsultarListaResultados.php?categoria_id="+idResultado;
        System.out.println("SERVER: "+url);
        txtinfo.setText("");
        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // Toast.makeText(actividad, "Conectados: "+response, Toast.LENGTH_LONG).show();

                try {
                    JSONArray jsonArray = response.optJSONArray("resultados");
                    ResultadosCategoriaVo resultadosCategoriaVo;
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject =  jsonArray.getJSONObject(i);
                        resultadosCategoriaVo=new ResultadosCategoriaVo();

                        if (!jsonObject.getString("categoria_id").equals("0")){
                            resultadosCategoriaVo.setCategoria(idResultado);
                            resultadosCategoriaVo.setRegional(jsonObject.getString("regional"));
                            resultadosCategoriaVo.setNombreCentro(jsonObject.getString("centro"));
                            resultadosCategoriaVo.setResultadoDia1(jsonObject.getDouble("jornada1"));
                            resultadosCategoriaVo.setResultadoDia2(jsonObject.getDouble("jornada2"));
                            resultadosCategoriaVo.setResultadoDia3(jsonObject.getDouble("jornada3"));
                            resultadosCategoriaVo.setResultadoTotal(jsonObject.getDouble("total"));
                            listaResultadosCategorias.add(resultadosCategoriaVo);
                        }else{
                            txtinfo.setText("No Hay Datos!!!");
                            showProgress(false);
                          //  layoutValidacion.setVisibility(View.VISIBLE);
                        }
                    }
                 //   if (idResultado.equalsIgnoreCase("0")){
                      //  layoutValidacion.setVisibility(View.INVISIBLE);
                        registrarResultadosBd(idResultado);
                        obtenerListaResultados(idResultado);
                 //   }


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
            cargarWebService(resultadoId,getString(R.string.ip2));
            ingreso=1;
        }else{
            if (ingreso==1){
                //   System.out.println("WEB SERVICE ALTERNO 2");
                cargarWebService(resultadoId,getString(R.string.ip3));
                ingreso=2;
            }else{
                //  System.out.println("CONEXION LOCAL");
                txtinfo.setText("No se pudo establecer conexión con el servidor, Intente mas tarde");
                obtenerListaResultados(resultadoId+"");
                showProgress(false);
            }
        }
    }

    private void registrarResultadosBd(String idCategoria) {
        bd=conectarBD();
        AgendaDiaVo agendaVo;
        ArrayList<ResultadosCategoriaVo> listaResultados=new ArrayList<>();
        ResultadosDao resultadosDao=new ResultadosDao();
        resultadosDao.eliminarResultado(bd,idCategoria);//Limpiamos el resultado
        for (int i =0; i<listaResultadosCategorias.size();i++){

            resultadosDao.insertarResultado(listaResultadosCategorias.get(i),bd);
        }

        bd.close();
    }

    public SQLiteDatabase conectarBD() {
        ConexionSQLiteHelper conexionBD=new ConexionSQLiteHelper(this.actividad, Constantes.BD_NAME,null,1);
        return conexionBD.getWritableDatabase();
    }

    private void obtenerListaResultados(String idResultado) {
      /*  listaResultadosCategorias=new ArrayList<>();
        listaResultadosCategorias.add(new ResultadosCategoriaVo("Android","Quindio","Centro de Comercio y Turismo",30.5,30.0,30.5,100.0));
        listaResultadosCategorias.add(new ResultadosCategoriaVo("Android","Quindio","Centro de La industria y la construcción",30.5,30.0,30.5,100.0));
        listaResultadosCategorias.add(new ResultadosCategoriaVo("Android","Antioquia","Centro de manufactura avanzado",30.5,30.0,30.5,100.0));
        listaResultadosCategorias.add(new ResultadosCategoriaVo("Android","Cauca","Centro de Comercio",30.5,30.0,30.5,100.0));
        listaResultadosCategorias.add(new ResultadosCategoriaVo("Android","Valle","Centro de electronica",30.5,30.0,30.5,100.0));
        listaResultadosCategorias.add(new ResultadosCategoriaVo("Android","Tolima","Centro de industria y Comercio",30.5,30.0,30.5,100.0));
        listaResultadosCategorias.add(new ResultadosCategoriaVo("Android","Huila","Centro de telemercadeo ",30.5,30.0,30.5,100.0));*/

        listaResultadosCategorias.clear();
        bd=conectarBD();
        ResultadosDao resultadosDao=new ResultadosDao();
        //  List<String> listaP=new ArrayList<String>();;
        ArrayList<ResultadosCategoriaVo> listaResultados=resultadosDao.consultarListaResultado(bd,idResultado);

        for (int i=0;i<listaResultados.size();i++){
            //  AgendaDiaVo miAgenda=listaAgendaDia.get(i);
            listaResultadosCategorias.add(listaResultados.get(i));
        }

        showProgress(false);

        miAdapter=new ResultadosCategoriasAdapter(listaResultadosCategorias);
        recyclerCategorias.setAdapter(miAdapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            this.actividad=(Activity) context;
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
