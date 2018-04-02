package co.quindio.sena.senasoftquindio2016.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

import co.quindio.sena.senasoftquindio2016.R;
import co.quindio.sena.senasoftquindio2016.clases.Constantes;
import co.quindio.sena.senasoftquindio2016.interfaces.IComunicaFragments;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LugarFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LugarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LugarFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    ImageView imagenLugar;
    ImageButton btnAtras,btnAdelante;
    Activity actividad;
    View vista;
    ArrayList<Integer> listaImagenesLugar;
    int posImagen;

    public LugarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LugarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LugarFragment newInstance(String param1, String param2) {
        LugarFragment fragment = new LugarFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        posImagen=0;
        vista=  inflater.inflate(R.layout.fragment_lugar, container, false);
        //valida que no exista el scroll horizontal, de esta manera muestra solo una imagen a la vez
        if (vista.findViewById(R.id.scrollImagenes)==null){
            imagenLugar= (ImageView) vista.findViewById(R.id.imgLugar);
            btnAdelante= (ImageButton) vista.findViewById(R.id.imageButtonAdelante);
            btnAtras= (ImageButton) vista.findViewById(R.id.imageButtonAtras);
            llenarListaImagenesLugar();

            btnAdelante.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    posImagen++;
                    asignarImagen();
                }
            });

            btnAtras.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    posImagen--;
                    asignarImagen();
                }
            });
            asignarImagen();
        }

        return vista;
    }

    private void asignarImagen() {
        if (posImagen>=listaImagenesLugar.size()){
            posImagen=0;
        }else {
            if (posImagen<0){
                posImagen=listaImagenesLugar.size()-1;
            }
        }
        imagenLugar.setImageResource(listaImagenesLugar.get(posImagen));
    }

    private void llenarListaImagenesLugar() {

        listaImagenesLugar=new ArrayList<>();
        listaImagenesLugar.add(R.drawable.convenciones1);
        listaImagenesLugar.add(R.drawable.convenciones2);
        listaImagenesLugar.add(R.drawable.convenciones3);

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
