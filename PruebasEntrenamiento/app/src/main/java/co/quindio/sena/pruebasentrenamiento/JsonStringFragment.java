package co.quindio.sena.pruebasentrenamiento;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link JsonStringFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link JsonStringFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JsonStringFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    View vista;
    RequestQueue request;
    Activity actividad;
    StringRequest jsonStringRequest;

    RecyclerView recyclerPersonas;
    ArrayList<PersonaVo> listaPersonas;
    PersonasAdapter miAdapter;


    public JsonStringFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JsonStringFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JsonStringFragment newInstance(String param1, String param2) {
        JsonStringFragment fragment = new JsonStringFragment();
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

        vista= inflater.inflate(R.layout.fragment_json_string, container, false);
        request= Volley.newRequestQueue(this.actividad);
        recyclerPersonas = (RecyclerView)vista.findViewById(R.id.list_json);

        cargarWebService();

        recyclerPersonas.setLayoutManager(new LinearLayoutManager(this.actividad));
        recyclerPersonas.setHasFixedSize(true);

        return vista;
    }

    private void cargarWebService() {
        System.out.println("***********************************************");
        System.out.println("***********************************************");
        System.out.println("INGRESA A CARGAR WS");
        String url =  getString(R.string.ip)+"/entrenamiento/wsJSONConsultarListaString.php";
        //String url="http://10.71.134.110/senasoftws/wsJSONConsultarLista.php";
        listaPersonas = new ArrayList<>();

        jsonStringRequest=new StringRequest(url, new Response.Listener<String>() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(String response) {
                try {

                    //lista con los objetos json
                    ArrayList<String> lista=new ArrayList<>();
                    String cad="";
                    /*recorro la cadena y almaceno cada caracter en otro string hasta que encuentre
                    * la llave de cierre, al hacerlo la cadena resultante se guarda en la lista y vuelve
                    * a iniciar para validar el otro registro*/
                    for (int i=0; i<response.length();i++){
                        if (response.charAt(i)!='}')
                        {
                            cad+=response.charAt(i);
                        }
                        else
                        {
                            cad+=response.charAt(i);
                            lista.add(cad);
                            cad="";
                        }
                    }
                    System.out.println();

                    PersonaVo miPersona;
                    JSONObject jsonObject;
                    for (int i = 0; i < lista.size(); i++) {
                        System.out.println(lista.get(i));
                        jsonObject=new JSONObject(lista.get(i));//obtengo la posicion donde se encuentra el objeto
                        miPersona=new PersonaVo();
                        miPersona.setId(Integer.parseInt(jsonObject.getString("id")));
                        miPersona.setNombre(jsonObject.getString("nombre"));
                        miPersona.setEdad(Integer.parseInt(jsonObject.getString("edad")));
                        miPersona.setTelefono(jsonObject.getString("telefono"));
                        miPersona.setProfesion(jsonObject.getString("profesion"));
                        miPersona.setDireccion(jsonObject.getString("direccion"));
                        listaPersonas.add(miPersona);
                    }






                    miAdapter=new PersonasAdapter(listaPersonas);
                    recyclerPersonas.setAdapter(miAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("ERRORRRR: " + volleyError);
                Toast.makeText(actividad, "ERRORRRR: " + volleyError, Toast.LENGTH_LONG).show();
                System.out.println("***********************************************");
                System.out.println("***********************************************");
            }
        });

        request.add(jsonStringRequest);

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
            //interfaceComunicaFragment=(IComunicaFragments) this.actividad;
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
