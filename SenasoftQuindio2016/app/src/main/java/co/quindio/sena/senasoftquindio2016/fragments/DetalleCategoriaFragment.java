package co.quindio.sena.senasoftquindio2016.fragments;

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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import co.quindio.sena.senasoftquindio2016.R;
import co.quindio.sena.senasoftquindio2016.adapters.TematicaCategoriasAdapter;
import co.quindio.sena.senasoftquindio2016.clases.Constantes;
import co.quindio.sena.senasoftquindio2016.clases.vo.CategoriasVo;
import co.quindio.sena.senasoftquindio2016.clases.vo.TematicaCategoriaVo;
import co.quindio.sena.senasoftquindio2016.interfaces.IComunicaFragments;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetalleCategoriaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetalleCategoriaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleCategoriaFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    Activity actividad;
    View vista;
    RecyclerView recyclerTematicas;
    ArrayList<TematicaCategoriaVo> listaTematicas;
    ImageView logoCategoria;
    TextView textObjetivo;
    TextView textDescObjetivo;
    TextView textTematica;
    LinearLayout layoutCategoria;

    IComunicaFragments interfaceComunicacionFragments;

    public DetalleCategoriaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleCategoriaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleCategoriaFragment newInstance(String param1, String param2) {
        DetalleCategoriaFragment fragment = new DetalleCategoriaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vista=  inflater.inflate(R.layout.fragment_detalle_categoria, container, false);

        textObjetivo= (TextView) vista.findViewById(R.id.txtobjetivo);
        textDescObjetivo= (TextView) vista.findViewById(R.id.txtDescObjetivo);
        textTematica= (TextView) vista.findViewById(R.id.textViewTematica);
        logoCategoria= (ImageView) vista.findViewById(R.id.imgCategoria);
        layoutCategoria= (LinearLayout) vista.findViewById(R.id.layoutId);

        Bundle objetoEnviadoBundle=getArguments();
        CategoriasVo categoriaVo=null;
        if (objetoEnviadoBundle!=null){
            categoriaVo= (CategoriasVo) objetoEnviadoBundle.getSerializable("categoria");
            Constantes.seleccionLineamiento=categoriaVo.getId();
            obtenerCategoria(categoriaVo);
        }

        recyclerTematicas = (RecyclerView)vista.findViewById(R.id.list_tematica_categoria);
        recyclerTematicas.setLayoutManager(new LinearLayoutManager(this.actividad));
        recyclerTematicas.setHasFixedSize(true);

        TematicaCategoriasAdapter miAdapter=new TematicaCategoriasAdapter(listaTematicas);

        ////
        miAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(actividad, "Seleccion " +listaTematicas.get(recyclerTematicas.getChildAdapterPosition(v)).getNombreCategoria(), Toast.LENGTH_SHORT).show();
              //   interfaceComunicacionFragments.enviaTematicaCategoria(listaTematicas.get(recyclerTematicas.getChildAdapterPosition(v)));
            }
        });
        ////
        recyclerTematicas.setAdapter(miAdapter);

        if (vista.findViewById(R.id.btnAtras)!=null){
            ImageButton btnAtras = (ImageButton) vista.findViewById(R.id.btnAtras);
            btnAtras.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment miFragment=null;
                    miFragment=new CategoriasFragment();
                    actividad.setTitle("Categorias");
                    getFragmentManager().beginTransaction().replace(R.id.contenedorFragments,miFragment).commit();
                }
            });
        }

        return vista;

    }

    public void obtenerCategoria(CategoriasVo categoria){
        textObjetivo.setText(categoria.getNombre());
        textDescObjetivo.setText(categoria.getInfo());
        logoCategoria.setImageResource(categoria.getIconoId());
        layoutCategoria.setBackgroundColor(getResources().getColor(categoria.getColorLogo()));
        textDescObjetivo.setBackgroundColor(getResources().getColor(categoria.getColorLogo()));

        if (categoria.getId()==16){
            textTematica.setText("Fases Proceso Creamás");
        }

        listaTematicas=categoria.getListaTematicas();
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
