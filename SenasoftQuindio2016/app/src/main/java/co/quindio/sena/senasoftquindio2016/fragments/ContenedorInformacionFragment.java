package co.quindio.sena.senasoftquindio2016.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import co.quindio.sena.senasoftquindio2016.MapaEventoActivity;
import co.quindio.sena.senasoftquindio2016.R;
import co.quindio.sena.senasoftquindio2016.clases.Constantes;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContenedorInformacionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContenedorInformacionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContenedorInformacionFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    Activity actividad;
    View vista;
    QueEsFragment queEsSenasotFragment;
    ParticipantesFragment participantesFragment;
    LugarFragment lugarFragment;

    public ContenedorInformacionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContenedorInformacionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContenedorInformacionFragment newInstance(String param1, String param2) {
        ContenedorInformacionFragment fragment = new ContenedorInformacionFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Constantes.ultimo_layout=R.layout.fragment_categorias;
        vista=  inflater.inflate(R.layout.fragment_contenedor_informacion, container, false);

        /*validamos que se esta trabajando desde una tablet en modo portrait, ya que si no existe el contenedor
        * es porque se esta en portrait*/
        if (vista.findViewById(R.id.contenedorInformacionGralLand7)==null){
            if (savedInstanceState!=null){
                return vista;
            }
            if (vista.findViewById(R.id.contenedorInformacionGralTablet10)==null){
                if (vista.findViewById(R.id.contenedorInformacionGralTablet10Port)==null){
                    InformacionFragment informacionFragment=new InformacionFragment();
                    getFragmentManager().beginTransaction().
                            replace(R.id.contenedorFragments,informacionFragment).commit();
                }else{
                    switch (Constantes.ultimo_layout_panel_info_pos){
                        case 0:
                            queEsSenasotFragment=new QueEsFragment();
                            getFragmentManager().beginTransaction().
                                    replace(R.id.fragContenidoTablet10,queEsSenasotFragment).commit();
                            break;
                        case 1:
                            lugarFragment=new LugarFragment();
                            getFragmentManager().beginTransaction().
                                    replace(R.id.fragContenidoTablet10,lugarFragment).commit();
                            break;
                    }
                    CardView cardInformacion = (CardView) vista.findViewById(R.id.cardInformacion);
                    CardView cardLugar = (CardView) vista.findViewById(R.id.cardLugar);
                    CardView cardMapa = (CardView) vista.findViewById(R.id.cardMapa);

                    cardInformacion.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            queEsSenasotFragment=new QueEsFragment();
                            getFragmentManager().beginTransaction().
                                    replace(R.id.fragContenidoTablet10,queEsSenasotFragment).commit();
                            Constantes.ultimo_layout_panel_info_pos=0;
                        }
                    });

                    cardLugar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            lugarFragment=new LugarFragment();
                            getFragmentManager().beginTransaction().
                                    replace(R.id.fragContenidoTablet10,lugarFragment).commit();
                            Constantes.ultimo_layout_panel_info_pos=1;
                        }
                    });

                    cardMapa.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(actividad,MapaEventoActivity.class);
                            startActivity(intent);
                        }
                    });

                }
            }else{
///////////////////////////////

                switch (Constantes.ultimo_layout_panel_info_pos){
                    case 0:
                        queEsSenasotFragment=new QueEsFragment();
                        getFragmentManager().beginTransaction().
                                replace(R.id.fragContenidoTablet10,queEsSenasotFragment).commit();
                        break;
                    case 1:
                        lugarFragment=new LugarFragment();
                        getFragmentManager().beginTransaction().
                                replace(R.id.fragContenidoTablet10,lugarFragment).commit();
                        break;
                }

                CardView cardInformacion = (CardView) vista.findViewById(R.id.cardInformacion);
                CardView cardLugar = (CardView) vista.findViewById(R.id.cardLugar);
                CardView cardMapa = (CardView) vista.findViewById(R.id.cardMapa);

                cardInformacion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        queEsSenasotFragment=new QueEsFragment();
                        getFragmentManager().beginTransaction().
                                replace(R.id.fragContenidoTablet10,queEsSenasotFragment).commit();
                        Constantes.ultimo_layout_panel_info_pos=0;
                    }
                });

                cardLugar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lugarFragment=new LugarFragment();
                        getFragmentManager().beginTransaction().
                                replace(R.id.fragContenidoTablet10,lugarFragment).commit();
                        Constantes.ultimo_layout_panel_info_pos=1;
                    }
                });

                cardMapa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(actividad,MapaEventoActivity.class);
                        startActivity(intent);
                    }
                });

////////////////////
            }
        }else{
               switch (Constantes.ultimo_layout_panel_info_pos){
                    case 0:
                        queEsSenasotFragment=new QueEsFragment();
                        getFragmentManager().beginTransaction().
                                replace(R.id.fragContenidoTablet7,queEsSenasotFragment).commit();
                        break;
                    case 1:
                        participantesFragment=new ParticipantesFragment();
                        getFragmentManager().beginTransaction().
                                replace(R.id.fragContenidoTablet7,participantesFragment).commit();
                        break;
                    case 2:
                        lugarFragment=new LugarFragment();
                        getFragmentManager().beginTransaction().
                                replace(R.id.fragContenidoTablet7,lugarFragment).commit();
                        break;
                }

                CardView cardInformacion = (CardView) vista.findViewById(R.id.cardInformacion);
                CardView cardParticipantes = (CardView) vista.findViewById(R.id.cardParticipantes);
                CardView cardLugar = (CardView) vista.findViewById(R.id.cardLugar);

                cardInformacion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        queEsSenasotFragment=new QueEsFragment();
                        getFragmentManager().beginTransaction().
                                replace(R.id.fragContenidoTablet7,queEsSenasotFragment).commit();
                        Constantes.ultimo_layout_panel_info_pos=0;
                    }
                });

                cardParticipantes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        participantesFragment=new ParticipantesFragment();
                        getFragmentManager().beginTransaction().
                                replace(R.id.fragContenidoTablet7,participantesFragment).commit();
                        Constantes.ultimo_layout_panel_info_pos=1;
                    }
                });

                cardLugar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lugarFragment=new LugarFragment();
                        getFragmentManager().beginTransaction().
                                replace(R.id.fragContenidoTablet7,lugarFragment).commit();
                        Constantes.ultimo_layout_panel_info_pos=2;
                    }
                });
             }
        Constantes.ultimo_layout=R.id.viewPagerInformacion;
        return vista;
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
           // interfaceComunicacionFragments=(IComunicaFragments) this.actividad;
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
