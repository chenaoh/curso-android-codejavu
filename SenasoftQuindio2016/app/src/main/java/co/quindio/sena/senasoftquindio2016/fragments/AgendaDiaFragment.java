package co.quindio.sena.senasoftquindio2016.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import co.quindio.sena.senasoftquindio2016.R;
import co.quindio.sena.senasoftquindio2016.adapters.AgendaDiaAdapter;
import co.quindio.sena.senasoftquindio2016.clases.vo.AgendaDiaVo;
import co.quindio.sena.senasoftquindio2016.clases.vo.AgendaVo;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AgendaDiaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AgendaDiaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgendaDiaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Activity actividad;
    View vista;
    RecyclerView recyclerAgendaDia;
    ArrayList<AgendaDiaVo> listaAgendaDia;
    TextView txtViewDia, txtInfo;
    private OnFragmentInteractionListener mListener;

    public AgendaDiaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgendaDiaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AgendaDiaFragment newInstance(String param1, String param2) {
        AgendaDiaFragment fragment = new AgendaDiaFragment();
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
        vista= inflater.inflate(R.layout.fragment_agenda_dia, container, false);
        recyclerAgendaDia = (RecyclerView)vista.findViewById(R.id.listAgendaDia);
        txtViewDia= (TextView) vista.findViewById(R.id.textViewDia);
        txtInfo= (TextView) vista.findViewById(R.id.txtinfo);

        obtenerListaAgendaDia();

        recyclerAgendaDia.setLayoutManager(new LinearLayoutManager(this.actividad));

        recyclerAgendaDia.setHasFixedSize(true);

        ImageButton btnAtras = (ImageButton) vista.findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment miFragment=null;
                miFragment=new AgendaFragment();
                actividad.setTitle("Agenda académica");
                getFragmentManager().beginTransaction().replace(R.id.contenedorFragments,miFragment).commit();
            }
        });

        AgendaDiaAdapter miAdapter=new AgendaDiaAdapter(listaAgendaDia);

        recyclerAgendaDia.setAdapter(miAdapter);
        return vista;
    }

    private void obtenerListaAgendaDia() {
        listaAgendaDia =new ArrayList<AgendaDiaVo>();

        //validamos si tenemos parametros
        Bundle parametro=getArguments();
        if (parametro!=null){
            AgendaVo agendaVo= (AgendaVo) parametro.getSerializable("agenda");
            listaAgendaDia=agendaVo.getListaAgendaDia();
            txtViewDia.setText(agendaVo.getDia());
            if (listaAgendaDia.size()<=0){
                txtInfo.setText("No se pudo establecer conexión con el servidor, verifique el acceso a internet e intente mas tarde");
            }else{
                txtInfo.setText("");
            }
        }


        //Alimentamos la lista con objetos de tipo Versiones usando el constructor Explicito
      /*  listaAgendaDia.add(new AgendaDiaVo(1,1,"Llegada","Llegada de aprendices e instructores","","","07:00 - 01:00 pm","Salon 1"));
        listaAgendaDia.add(new AgendaDiaVo(1,2,"Conferencia","Especialización Regional Inteligente. Politica de Transformación Digital para los sectores estratégicos del País a través de la Industria TI","Lina Taborda Directora de Politicas y Desarrollo TI del Ministerio TIC","Martes 25 de Octubre","09:00 a 10:00 am","Salon 1"));
        listaAgendaDia.add(new AgendaDiaVo(1,2,"Conferencia","Las TIC transformando la Educación","Fernando Bedoya, Andrea Escobar, Isabel Cristina De Avila, pendiente Sebastián por ubicar moderador - periodista de TIC de la region)","Martes 25 de Octubre","09:00 a 10:00 am","Salon 2"));
        listaAgendaDia.add(new AgendaDiaVo(1,2,"Conferencia","Especialización Regional Inteligente. Politica de Transformación Digital para los sectores estratégicos del País a través de la Industria TI","Lina Taborda Directora de Politicas y Desarrollo TI del Ministerio TIC","Martes 25 de Octubre","09:00 a 10:00 am","Salon 1"));
        listaAgendaDia.add(new AgendaDiaVo(1,2,"Conferencia","Las TIC transformando la Educación","Fernando Bedoya, Andrea Escobar, Isabel Cristina De Avila, pendiente Sebastián por ubicar moderador - periodista de TIC de la region)","Martes 25 de Octubre","09:00 a 10:00 am","Salon 2"));
        listaAgendaDia.add(new AgendaDiaVo(1,2,"Conferencia","Especialización Regional Inteligente. Politica de Transformación Digital para los sectores estratégicos del País a través de la Industria TI","Lina Taborda Directora de Politicas y Desarrollo TI del Ministerio TIC","Martes 25 de Octubre","09:00 a 10:00 am","Salon 1"));
        listaAgendaDia.add(new AgendaDiaVo(1,2,"Conferencia","Las TIC transformando la Educación","Fernando Bedoya, Andrea Escobar, Isabel Cristina De Avila, pendiente Sebastián por ubicar moderador - periodista de TIC de la region)","Martes 25 de Octubre","09:00 a 10:00 am","Salon 2"));
        */

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
