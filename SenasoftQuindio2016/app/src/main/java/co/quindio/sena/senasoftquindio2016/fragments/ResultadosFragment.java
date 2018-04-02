package co.quindio.sena.senasoftquindio2016.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import co.quindio.sena.senasoftquindio2016.R;
import co.quindio.sena.senasoftquindio2016.adapters.ResultadosAdapter;
import co.quindio.sena.senasoftquindio2016.clases.Constantes;
import co.quindio.sena.senasoftquindio2016.clases.vo.ResultadosVo;
import co.quindio.sena.senasoftquindio2016.dialogos.JornadaCategoriaDialog;
import co.quindio.sena.senasoftquindio2016.dialogos.ResultadosMensajeDiaglog;
import co.quindio.sena.senasoftquindio2016.interfaces.IComunicaFragments;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ResultadosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ResultadosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultadosFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    Activity actividad;
    View vista;
    RecyclerView recyclerCategorias;
    ArrayList<ResultadosVo> listaCategorias;

    IComunicaFragments interfaceComunicacionFragments;

    public ResultadosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultadosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultadosFragment newInstance(String param1, String param2) {
        ResultadosFragment fragment = new ResultadosFragment();
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
        vista=  inflater.inflate(R.layout.fragment_resultados, container, false);
        recyclerCategorias = (RecyclerView)vista.findViewById(R.id.list_card_resultados);
        obtenerListaVersiones();

        if (vista.findViewById(R.id.resultadosLand7)==null){
            recyclerCategorias.setLayoutManager(new GridLayoutManager(this.actividad,3));
        }else{
            recyclerCategorias.setLayoutManager(new GridLayoutManager(this.actividad,5));
        }

        recyclerCategorias.setHasFixedSize(true);

        ResultadosAdapter miAdapter=new ResultadosAdapter(listaCategorias);

        ////
        miAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(actividad, "Seleccion " +listaCategorias.get(recyclerCategorias.getChildAdapterPosition(v)) .getNombreCategoria(), Toast.LENGTH_SHORT).show();
                interfaceComunicacionFragments.enviaCategoriaResultado(listaCategorias.get(recyclerCategorias.getChildAdapterPosition(v)));
            }
        });
        ////
        recyclerCategorias.setAdapter(miAdapter);

        return vista;
    }


    private void obtenerListaVersiones() {
        //Instanciamos la lista de Versiones
        listaCategorias =new ArrayList<ResultadosVo>();

        //Alimentamos la lista con objetos de tipo Versiones usando el constructor Explicito
        listaCategorias.add(new ResultadosVo(1,getString(R.string.algoritmiaTitle),getString(R.string.algoritmiaDescrip),R.drawable.algoritmia,R.drawable.algoritmia_ico,R.color.colorPrimaryVersion1));
        listaCategorias.add(new ResultadosVo(4,getString(R.string.androidTitle),getString(R.string.androidDescrip),R.drawable.android,R.drawable.android_ico,R.color.colorPrimaryVersion1));
        listaCategorias.add(new ResultadosVo(2,getString(R.string.animacionTitle),getString(R.string.animacionDescrip),R.drawable.animacion,R.drawable.animacion_ico,R.color.colorPrimary));
        listaCategorias.add(new ResultadosVo(6,getString(R.string.bdTitle),getString(R.string.bdDescrip),R.drawable.bd,R.drawable.bd_ico,R.color.colorPrimaryVersion1));
        listaCategorias.add(new ResultadosVo(8,getString(R.string.hardeningTitle),getString(R.string.hardeningDescrip),R.drawable.instalacion_so,R.drawable.instalacion_so_ico,R.color.colorAccent));
        listaCategorias.add(new ResultadosVo(9,getString(R.string.javaTitle),getString(R.string.javaDescrip),R.drawable.java,R.drawable.java_ico,R.color.colorPrimaryVersion1));
        listaCategorias.add(new ResultadosVo(11,getString(R.string.multimediaTitle),getString(R.string.multimediaDescrip),R.drawable.multimedia,R.drawable.multimedia_ico,R.color.colorPrimary));
        listaCategorias.add(new ResultadosVo(3,getString(R.string.netTitle),getString(R.string.netDescrip), R.drawable.net,R.drawable.net_ico,R.color.colorPrimaryVersion1));
        listaCategorias.add(new ResultadosVo(5,getString(R.string.phpTitle),getString(R.string.phpDescrip), R.drawable.php,R.drawable.php_ico,R.color.colorPrimaryVersion1));
        listaCategorias.add(new ResultadosVo(10,getString(R.string.prodMultimediaTitle),getString(R.string.prodMultimediaDescrip),R.drawable.produccion_multimedia,R.drawable.produccion_multimedia_ico,R.color.colorPrimary));
        listaCategorias.add(new ResultadosVo(13,getString(R.string.redesTitle),getString(R.string.redesDescrip), R.drawable.redes,R.drawable.redes_ico,R.color.colorAccent));
        listaCategorias.add(new ResultadosVo(12,getString(R.string.scrumTitle),getString(R.string.scrumDescrip), R.drawable.scrum,R.drawable.scrum_ico,R.color.colorPrimaryVersion1));
        listaCategorias.add(new ResultadosVo(14,getString(R.string.sistemasTitle),getString(R.string.sistemasDescrip), R.drawable.so,R.drawable.so_ico,R.color.colorAccent));
        listaCategorias.add(new ResultadosVo(7,getString(R.string.disenoTitle),getString(R.string.disenoDescrip), R.drawable.uml,R.drawable.uml_ico,R.color.colorPrimaryVersion1));
        listaCategorias.add(new ResultadosVo(15,getString(R.string.videoJuegosTitle),getString(R.string.videoJuegosDescrip), R.drawable.videojuegos,R.drawable.videojuegos_ico,R.color.colorPrimary));
        listaCategorias.add(new ResultadosVo(16,getString(R.string.creamasTitle),getString(R.string.creamasDescrip), R.drawable.rueda_creamas,R.drawable.creamas_ico,R.color.colorDarkVersion2));
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
