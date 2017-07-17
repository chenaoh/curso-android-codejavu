package co.quindio.sena.ejemplomaestrodetalle.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import co.quindio.sena.ejemplomaestrodetalle.R;
import co.quindio.sena.ejemplomaestrodetalle.adaptadores.AdaptadorPersonajes;
import co.quindio.sena.ejemplomaestrodetalle.entidades.PersonajeVo;
import co.quindio.sena.ejemplomaestrodetalle.interfaces.IComunicaFragments;

public class ListaPersonajesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ArrayList<PersonajeVo> listaPersonaje;
    RecyclerView recyclerPersonajes;

    Activity actividad;
    IComunicaFragments interfaceComunicaFragments;

    public ListaPersonajesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaPersonajesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaPersonajesFragment newInstance(String param1, String param2) {
        ListaPersonajesFragment fragment = new ListaPersonajesFragment();
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
        View vista=inflater.inflate(R.layout.fragment_lista_personajes, container, false);

        listaPersonaje=new ArrayList<>();
        recyclerPersonajes= (RecyclerView) vista.findViewById(R.id.recyclerId);
        recyclerPersonajes.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarListaPersonajes();

        AdaptadorPersonajes adapter=new AdaptadorPersonajes(listaPersonaje);
        recyclerPersonajes.setAdapter(adapter);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Seleccion: "+
                        listaPersonaje.get(recyclerPersonajes.
                                getChildAdapterPosition(view)).getNombre(),Toast.LENGTH_SHORT).show();

                interfaceComunicaFragments.enviarPersonaje(listaPersonaje.get(recyclerPersonajes.getChildAdapterPosition(view)));
            }
        });


        return vista;
    }

    private void llenarListaPersonajes() {
        listaPersonaje.add(new PersonajeVo(getString(R.string.goku_nombre), getString(R.string.goku_descripcion_corta),
                getString(R.string.goku_descripcion_Larga), R.drawable.goku_cara,R.drawable.goku_detalle));

        listaPersonaje.add(new PersonajeVo(getString(R.string.gohan_nombre), getString(R.string.gohan_descripcion_corta),
                getString(R.string.gohan_descripcion_Larga), R.drawable.gohan_cara,R.drawable.gohan_detalle));

        listaPersonaje.add(new PersonajeVo(getString(R.string.goten_nombre), getString(R.string.goten_descripcion_corta),
                getString(R.string.goten_descripcion_Larga), R.drawable.goten_cara,R.drawable.goten_detalle));

        listaPersonaje.add(new PersonajeVo(getString(R.string.krilin_nombre), getString(R.string.krilin_descripcion_corta),
                getString(R.string.krilin_descripcion_Larga), R.drawable.krilin_cara,R.drawable.krilin_detalle));

        listaPersonaje.add(new PersonajeVo(getString(R.string.picoro_nombre), getString(R.string.picoro_descripcion_corta),
                getString(R.string.picoro_descripcion_Larga), R.drawable.picoro_cara,R.drawable.picoro_detalle));

        listaPersonaje.add(new PersonajeVo(getString(R.string.trunks_nombre), getString(R.string.trunks_descripcion_corta),
                getString(R.string.trunks_descripcion_Larga), R.drawable.trunks_cara,R.drawable.trunks_detalle));

        listaPersonaje.add(new PersonajeVo(getString(R.string.vegueta_nombre), getString(R.string.vegueta_descripcion_corta),
                getString(R.string.vegueta_descripcion_Larga), R.drawable.vegueta_cara,R.drawable.vegueta_detalle));
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

        if(context instanceof Activity){
            this.actividad= (Activity) context;
            interfaceComunicaFragments= (IComunicaFragments) this.actividad;
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
