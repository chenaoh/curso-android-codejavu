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
import android.widget.Toast;

import java.util.ArrayList;

import co.quindio.sena.senasoftquindio2016.R;
import co.quindio.sena.senasoftquindio2016.adapters.CategoriasAdapter;
import co.quindio.sena.senasoftquindio2016.clases.Constantes;
import co.quindio.sena.senasoftquindio2016.clases.vo.CategoriasVo;
import co.quindio.sena.senasoftquindio2016.clases.vo.TematicaCategoriaVo;
import co.quindio.sena.senasoftquindio2016.interfaces.IComunicaFragments;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListaCategoriasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListaCategoriasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaCategoriasFragment extends Fragment {
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
    RecyclerView recyclerVersiones;
    ArrayList<CategoriasVo> listaCategorias;

    int arregloImagenes[]=new int[16];
    int arregloIconos[]=new int[16];

    IComunicaFragments interfaceComunicacionFragments;

    public ListaCategoriasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaCategoriasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaCategoriasFragment newInstance(String param1, String param2) {
        ListaCategoriasFragment fragment = new ListaCategoriasFragment();
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
        vista=  inflater.inflate(R.layout.fragment_lista_categorias, container, false);
        recyclerVersiones = (RecyclerView)vista.findViewById(R.id.list_card_recycler);
        llenarListaImagenes();
        obtenerListaVersiones();

        if (vista.findViewById(R.id.listaPortrait)!=null){
            Constantes.PORTRAIT=true;
        }else{
            Constantes.PORTRAIT=false;
        }


        recyclerVersiones.setLayoutManager(new LinearLayoutManager(this.actividad));
        recyclerVersiones.setHasFixedSize(true);

        CategoriasAdapter miAdapter=new CategoriasAdapter(listaCategorias);

        ////
        miAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(actividad, "Seleccion categoria " + listaCategorias.get(recyclerVersiones.getChildAdapterPosition(v))  .getNombre(), Toast.LENGTH_SHORT).show();
                interfaceComunicacionFragments.enviaCategoria(listaCategorias.get(recyclerVersiones.getChildAdapterPosition(v)));
            }
        });
        ////
        recyclerVersiones.setAdapter(miAdapter);

        return vista;
    }

    private void llenarListaImagenes() {

        arregloImagenes[0]=R.drawable.algoritmia;       arregloImagenes[1]=R.drawable.net;                   arregloImagenes[2]=R.drawable.android;     arregloImagenes[3]=R.drawable.php;
        arregloImagenes[4]=R.drawable.bd;               arregloImagenes[5]=R.drawable.uml;                   arregloImagenes[6]=R.drawable.java;        arregloImagenes[7]=R.drawable.scrum;
        arregloImagenes[8]=R.drawable.animacion;        arregloImagenes[9]=R.drawable.produccion_multimedia; arregloImagenes[10]=R.drawable.multimedia; arregloImagenes[11]=R.drawable.videojuegos;
        arregloImagenes[12]=R.drawable.instalacion_so;  arregloImagenes[13]=R.drawable.redes;                arregloImagenes[14]=R.drawable.so;
        arregloImagenes[15]=R.drawable.rueda_creamas;

      /*  if (vista.findViewById(R.id.listaPortrait)!=null){

            arregloIconos[0]=R.drawable.algoritmia;         arregloIconos[1]=R.drawable.net;                    arregloIconos[2]=R.drawable.android;     arregloIconos[3]=R.drawable.php;
            arregloIconos[4]=R.drawable.bd;                 arregloIconos[5]=R.drawable.uml;                    arregloIconos[6]=R.drawable.java;        arregloIconos[7]=R.drawable.scrum;
            arregloIconos[8]=R.drawable.animacion;          arregloIconos[9]=R.drawable.produccion_multimedia;  arregloIconos[10]=R.drawable.multimedia; arregloIconos[11]=R.drawable.videojuegos;
            arregloIconos[12]=R.drawable.instalacion_so;    arregloIconos[13]=R.drawable.redes;                 arregloIconos[14]=R.drawable.so;
            arregloIconos[15]=R.drawable.rueda_creamas;
        }else{*/
            arregloIconos[0]=R.drawable.algoritmia_ico;     arregloIconos[1]=R.drawable.net_ico;                arregloIconos[2]=R.drawable.android_ico;    arregloIconos[3]=R.drawable.php_ico;
            arregloIconos[4]=R.drawable.bd_ico;             arregloIconos[5]=R.drawable.uml_ico;                arregloIconos[6]=R.drawable.java_ico;       arregloIconos[7]=R.drawable.scrum_ico;
            arregloIconos[8]=R.drawable.animacion_ico;      arregloIconos[9]=R.drawable.produccion_multimedia_ico; arregloIconos[10]=R.drawable.multimedia_ico; arregloIconos[11]=R.drawable.videojuegos_ico;
            arregloIconos[12]=R.drawable.instalacion_so_ico;arregloIconos[13]=R.drawable.redes_ico;             arregloIconos[14]=R.drawable.so_ico;
            arregloIconos[15]=R.drawable.creamas_ico;
        //}
    }

    private void obtenerListaVersiones() {
        //Instanciamos la lista de Versiones
        listaCategorias =new ArrayList<CategoriasVo>();

        //Alimentamos la lista con objetos de tipo Versiones usando el constructor Explicito
        listaCategorias.add(new CategoriasVo(1,getString(R.string.algoritmiaTitle),getString(R.string.algoritmiaDescrip), arregloImagenes[0],arregloIconos[0],obtenerListaTematicas(R.string.algoritmiaTitle),R.color.colorPrimaryVersion1));
        listaCategorias.add(new CategoriasVo(3,getString(R.string.netTitle),getString(R.string.netDescrip),arregloImagenes[1],arregloIconos[1],obtenerListaTematicas(R.string.netTitle),R.color.colorPrimaryVersion1));
        listaCategorias.add(new CategoriasVo(4,getString(R.string.androidTitle),getString(R.string.androidDescrip),arregloImagenes[2],arregloIconos[2],obtenerListaTematicas(R.string.androidTitle),R.color.colorPrimaryVersion1));
        listaCategorias.add(new CategoriasVo(5,getString(R.string.phpTitle),getString(R.string.phpDescrip),arregloImagenes[3],arregloIconos[3],obtenerListaTematicas(R.string.phpTitle),R.color.colorPrimaryVersion1));
        listaCategorias.add(new CategoriasVo(6,getString(R.string.bdTitle),getString(R.string.bdDescrip),arregloImagenes[4],arregloIconos[4],obtenerListaTematicas(R.string.bdTitle),R.color.colorPrimaryVersion1));
        listaCategorias.add(new CategoriasVo(7,getString(R.string.disenoTitle),getString(R.string.disenoDescrip),arregloImagenes[5],arregloIconos[5],obtenerListaTematicas(R.string.disenoTitle),R.color.colorPrimaryVersion1));
        listaCategorias.add(new CategoriasVo(9,getString(R.string.javaTitle),getString(R.string.javaDescrip),arregloImagenes[6],arregloIconos[6],obtenerListaTematicas(R.string.javaTitle),R.color.colorPrimaryVersion1));
        listaCategorias.add(new CategoriasVo(12,getString(R.string.scrumTitle),getString(R.string.scrumDescrip), arregloImagenes[7],arregloIconos[7],obtenerListaTematicas(R.string.scrumTitle),R.color.colorPrimaryVersion1));
        listaCategorias.add(new CategoriasVo(2,getString(R.string.animacionTitle),getString(R.string.animacionDescrip),arregloImagenes[8],arregloIconos[8],obtenerListaTematicas(R.string.animacionTitle),R.color.colorPrimary));
        listaCategorias.add(new CategoriasVo(10,getString(R.string.prodMultimediaTitle),getString(R.string.prodMultimediaDescrip),arregloImagenes[9],arregloIconos[9],obtenerListaTematicas(R.string.prodMultimediaTitle),R.color.colorPrimary));
        listaCategorias.add(new CategoriasVo(11,getString(R.string.multimediaTitle),getString(R.string.multimediaDescrip),arregloImagenes[10],arregloIconos[10],obtenerListaTematicas(R.string.multimediaTitle),R.color.colorPrimary));
        listaCategorias.add(new CategoriasVo(15,getString(R.string.videoJuegosTitle),getString(R.string.videoJuegosDescrip),arregloImagenes[11],arregloIconos[11],obtenerListaTematicas(R.string.videoJuegosTitle),R.color.colorPrimary));
        listaCategorias.add(new CategoriasVo(8,getString(R.string.hardeningTitle),getString(R.string.hardeningDescrip),arregloImagenes[12],arregloIconos[12],obtenerListaTematicas(R.string.hardeningTitle),R.color.colorAccent));
        listaCategorias.add(new CategoriasVo(13,getString(R.string.redesTitle),getString(R.string.redesDescrip),arregloImagenes[13],arregloIconos[13],obtenerListaTematicas(R.string.redesTitle),R.color.colorAccent));
        listaCategorias.add(new CategoriasVo(14,getString(R.string.sistemasTitle),getString(R.string.sistemasDescrip),arregloImagenes[14],arregloIconos[14],obtenerListaTematicas(R.string.sistemasTitle),R.color.colorAccent));
        listaCategorias.add(new CategoriasVo(16,getString(R.string.creamasTitle),getString(R.string.creamasDescrip),arregloImagenes[15],arregloIconos[15],obtenerListaTematicas(R.string.creamasTitle),R.color.colorDarkVersion2));
        //listaCategorias.add(new CategoriasVo(9,getString(R.string.ofimaticaTitle),getString(R.string.ofimaticaDescrip),R.drawable.ofimatica));

    }

    private ArrayList<TematicaCategoriaVo> obtenerListaTematicas(int categoria) {
        ArrayList<TematicaCategoriaVo> tematicasList=new ArrayList<>();

        switch (categoria){
            case R.string.algoritmiaTitle:
                tematicasList.add(new TematicaCategoriaVo(1,getString(R.string.algoritmiaTitle),getString(R.string.jornada_uno),getString(R.string.proceso_algoritmia_uno),getString(R.string.proceso_algoritmia_uno_desc)));
                tematicasList.add(new TematicaCategoriaVo(2,getString(R.string.algoritmiaTitle),getString(R.string.jornada_dos),getString(R.string.proceso_algoritmia_dos),getString(R.string.proceso_algoritmia_dos_desc)));
                tematicasList.add(new TematicaCategoriaVo(3,getString(R.string.algoritmiaTitle),getString(R.string.jornada_tres),getString(R.string.proceso_algoritmia_tres),getString(R.string.proceso_algoritmia_tres_desc)));
                break;
            case R.string.netTitle:
                tematicasList.add(new TematicaCategoriaVo(1,getString(R.string.netTitle),getString(R.string.jornada_uno),getString(R.string.proceso_net_uno),getString(R.string.proceso_net_uno_desc)));
                tematicasList.add(new TematicaCategoriaVo(2,getString(R.string.netTitle),getString(R.string.jornada_dos),getString(R.string.proceso_net_dos),getString(R.string.proceso_net_dos_desc)));
                tematicasList.add(new TematicaCategoriaVo(3,getString(R.string.netTitle),getString(R.string.jornada_tres),getString(R.string.proceso_net_tres),getString(R.string.proceso_net_tres_desc)));
                break;
            case R.string.androidTitle:
                tematicasList.add(new TematicaCategoriaVo(1,getString(R.string.androidTitle),getString(R.string.jornada_uno),getString(R.string.proceso_android_uno),getString(R.string.proceso_android_uno_desc)));
                tematicasList.add(new TematicaCategoriaVo(2,getString(R.string.androidTitle),getString(R.string.jornada_dos),getString(R.string.proceso_android_dos),getString(R.string.proceso_android_dos_desc)));
                tematicasList.add(new TematicaCategoriaVo(3,getString(R.string.androidTitle),getString(R.string.jornada_tres),getString(R.string.proceso_android_tres),getString(R.string.proceso_android_tres_desc)));
                break;
            case R.string.phpTitle:
                tematicasList.add(new TematicaCategoriaVo(1,getString(R.string.phpTitle),getString(R.string.jornada_uno),getString(R.string.proceso_php_uno),getString(R.string.proceso_php_uno_desc)));
                tematicasList.add(new TematicaCategoriaVo(2,getString(R.string.phpTitle),getString(R.string.jornada_dos),getString(R.string.proceso_php_dos),getString(R.string.proceso_php_dos_desc)));
                tematicasList.add(new TematicaCategoriaVo(3,getString(R.string.phpTitle),getString(R.string.jornada_tres),getString(R.string.proceso_php_tres),getString(R.string.proceso_php_tres_desc)));
                break;
            case R.string.bdTitle:
                tematicasList.add(new TematicaCategoriaVo(1,getString(R.string.bdTitle),getString(R.string.jornada_uno),getString(R.string.proceso_bd_uno),getString(R.string.proceso_bd_uno_desc)));
                tematicasList.add(new TematicaCategoriaVo(2,getString(R.string.bdTitle),getString(R.string.jornada_dos),getString(R.string.proceso_bd_dos),getString(R.string.proceso_bd_dos_desc)));
                tematicasList.add(new TematicaCategoriaVo(3,getString(R.string.bdTitle),getString(R.string.jornada_tres),getString(R.string.proceso_bd_tres),getString(R.string.proceso_bd_tres_desc)));
                break;
            case R.string.disenoTitle:
                tematicasList.add(new TematicaCategoriaVo(1,getString(R.string.disenoTitle),getString(R.string.jornada_uno),getString(R.string.proceso_uml_uno),getString(R.string.proceso_uml_uno_desc)));
                tematicasList.add(new TematicaCategoriaVo(2,getString(R.string.disenoTitle),getString(R.string.jornada_dos),getString(R.string.proceso_uml_dos),getString(R.string.proceso_uml_dos_desc)));
                tematicasList.add(new TematicaCategoriaVo(3,getString(R.string.disenoTitle),getString(R.string.jornada_tres),getString(R.string.proceso_uml_tres),getString(R.string.proceso_uml_tres_desc)));
                break;
            case R.string.javaTitle:
                tematicasList.add(new TematicaCategoriaVo(1,getString(R.string.javaTitle),getString(R.string.jornada_uno),getString(R.string.proceso_java_uno),getString(R.string.proceso_java_uno_desc)));
                tematicasList.add(new TematicaCategoriaVo(2,getString(R.string.javaTitle),getString(R.string.jornada_dos),getString(R.string.proceso_java_dos),getString(R.string.proceso_java_dos_desc)));
                tematicasList.add(new TematicaCategoriaVo(3,getString(R.string.javaTitle),getString(R.string.jornada_tres),getString(R.string.proceso_java_tres),getString(R.string.proceso_java_tres_desc)));
                break;
            case R.string.scrumTitle:
                tematicasList.add(new TematicaCategoriaVo(1,getString(R.string.scrumTitle),getString(R.string.jornada_uno),getString(R.string.proceso_scrum_uno),getString(R.string.proceso_scrum_uno_desc)));
                tematicasList.add(new TematicaCategoriaVo(2,getString(R.string.scrumTitle),getString(R.string.jornada_dos),getString(R.string.proceso_scrum_dos),getString(R.string.proceso_scrum_dos_desc)));
                tematicasList.add(new TematicaCategoriaVo(3,getString(R.string.scrumTitle),getString(R.string.jornada_tres),getString(R.string.proceso_scrum_tres),getString(R.string.proceso_scrum_tres_desc)));
                break;
            case R.string.animacionTitle:
                tematicasList.add(new TematicaCategoriaVo(1,getString(R.string.animacionTitle),getString(R.string.jornada_uno),getString(R.string.proceso_animacion3d_uno),getString(R.string.proceso_animacion3d_uno_desc)));
                tematicasList.add(new TematicaCategoriaVo(2,getString(R.string.animacionTitle),getString(R.string.jornada_dos),getString(R.string.proceso_animacion3d_dos),getString(R.string.proceso_animacion3d_dos_desc)));
                tematicasList.add(new TematicaCategoriaVo(3,getString(R.string.animacionTitle),getString(R.string.jornada_tres),getString(R.string.proceso_animacion3d_tres),getString(R.string.proceso_animacion3d_tres_desc)));
                break;
            case R.string.prodMultimediaTitle:
                tematicasList.add(new TematicaCategoriaVo(1,getString(R.string.prodMultimediaTitle),getString(R.string.jornada_uno),getString(R.string.proceso_prod_medios_uno),getString(R.string.proceso_prod_medios_uno_desc)));
                tematicasList.add(new TematicaCategoriaVo(2,getString(R.string.prodMultimediaTitle),getString(R.string.jornada_dos),getString(R.string.proceso_prod_medios_dos),getString(R.string.proceso_prod_medios_dos_desc)));
                tematicasList.add(new TematicaCategoriaVo(3,getString(R.string.prodMultimediaTitle),getString(R.string.jornada_tres),getString(R.string.proceso_prod_medios_tres),getString(R.string.proceso_prod_medios_tres_desc)));
                break;
            case R.string.multimediaTitle:
                tematicasList.add(new TematicaCategoriaVo(1,getString(R.string.multimediaTitle),getString(R.string.jornada_uno),getString(R.string.proceso_multimedia_uno),getString(R.string.proceso_multimedia_uno_desc)));
                tematicasList.add(new TematicaCategoriaVo(2,getString(R.string.multimediaTitle),getString(R.string.jornada_dos),getString(R.string.proceso_multimedia_dos),getString(R.string.proceso_multimedia_dos_desc)));
                tematicasList.add(new TematicaCategoriaVo(3,getString(R.string.multimediaTitle),getString(R.string.jornada_tres),getString(R.string.proceso_multimedia_tres),getString(R.string.proceso_multimedia_tres_desc)));
                break;
            case R.string.videoJuegosTitle:
                tematicasList.add(new TematicaCategoriaVo(1,getString(R.string.videoJuegosTitle),getString(R.string.jornada_uno),getString(R.string.proceso_videojuegos_uno),getString(R.string.proceso_videojuegos_uno_desc)));
                tematicasList.add(new TematicaCategoriaVo(2,getString(R.string.videoJuegosTitle),getString(R.string.jornada_dos),getString(R.string.proceso_videojuegos_dos),getString(R.string.proceso_videojuegos_dos_desc)));
                tematicasList.add(new TematicaCategoriaVo(3,getString(R.string.videoJuegosTitle),getString(R.string.jornada_tres),getString(R.string.proceso_videojuegos_tres),getString(R.string.proceso_videojuegos_tres_desc)));
                break;
            case R.string.hardeningTitle:
                tematicasList.add(new TematicaCategoriaVo(1,getString(R.string.hardeningTitle),getString(R.string.jornada_uno),getString(R.string.proceso_hardening_uno),getString(R.string.proceso_hardening_uno_desc)));
                tematicasList.add(new TematicaCategoriaVo(2,getString(R.string.hardeningTitle),getString(R.string.jornada_dos),getString(R.string.proceso_hardening_dos),getString(R.string.proceso_hardening_dos_desc)));
                tematicasList.add(new TematicaCategoriaVo(3,getString(R.string.hardeningTitle),getString(R.string.jornada_tres),getString(R.string.proceso_hardening_tres),getString(R.string.proceso_hardening_tres_desc)));
                break;
            case R.string.redesTitle:
                tematicasList.add(new TematicaCategoriaVo(1,getString(R.string.redesTitle),getString(R.string.jornada_uno),getString(R.string.proceso_redes_uno),getString(R.string.proceso_redes_uno_desc)));
                tematicasList.add(new TematicaCategoriaVo(2,getString(R.string.redesTitle),getString(R.string.jornada_dos),getString(R.string.proceso_redes_dos),getString(R.string.proceso_redes_dos_desc)));
                tematicasList.add(new TematicaCategoriaVo(3,getString(R.string.redesTitle),getString(R.string.jornada_tres),getString(R.string.proceso_redes_tres),getString(R.string.proceso_redes_tres_desc)));
                break;
            case R.string.sistemasTitle:
                tematicasList.add(new TematicaCategoriaVo(1,getString(R.string.sistemasTitle),getString(R.string.jornada_uno),getString(R.string.proceso_so_uno),getString(R.string.proceso_so_uno_desc)));
                tematicasList.add(new TematicaCategoriaVo(2,getString(R.string.sistemasTitle),getString(R.string.jornada_dos),getString(R.string.proceso_so_dos),getString(R.string.proceso_so_dos_desc)));
                tematicasList.add(new TematicaCategoriaVo(3,getString(R.string.sistemasTitle),getString(R.string.jornada_tres),getString(R.string.proceso_so_tres),getString(R.string.proceso_so_tres_desc)));
                break;
            case R.string.creamasTitle:
                tematicasList.add(new TematicaCategoriaVo(1,getString(R.string.creamasTitle),"Fase Creer",getString(R.string.proceso_creamas_uno),getString(R.string.proceso_creamas_uno_desc)));
                tematicasList.add(new TematicaCategoriaVo(2,getString(R.string.creamasTitle),"Fase Crear",getString(R.string.proceso_creamas_dos),getString(R.string.proceso_creamas_dos_desc)));
                tematicasList.add(new TematicaCategoriaVo(3,getString(R.string.creamasTitle),"Fase Creamás",getString(R.string.proceso_creamas_tres),getString(R.string.proceso_creamas_tres_desc)));
                break;
        }

        return tematicasList;
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
