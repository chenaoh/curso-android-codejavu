package co.quindio.sena.senasoftquindio2016.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import co.quindio.sena.senasoftquindio2016.R;
import co.quindio.sena.senasoftquindio2016.adapters.SeccionesInformacionAdapter;
import co.quindio.sena.senasoftquindio2016.clases.Constantes;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreaMasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreaMasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreaMasFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    Activity actividad;
    View vista;
    Bundle savedInstanceState;

    private AppBarLayout appBar;
    private TabLayout pestanas;
    private ViewPager viewPager;

    public CreaMasFragment() {
        // Required empty public constructor
        //setRetainInstance(true);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreaMasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreaMasFragment newInstance(String param1, String param2) {
        CreaMasFragment fragment = new CreaMasFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Constantes.rotacionInfoCreamas=0;
        this.savedInstanceState=savedInstanceState;
        vista=inflater.inflate(R.layout.fragment_crea_mas, container, false);

        if (savedInstanceState == null) {

            View padre = (View) container.getParent();

            if (appBar==null){
                appBar = (AppBarLayout) padre.findViewById(R.id.appbar);
                pestanas = new TabLayout(getActivity());
                //appBar = (AppBarLayout) vista.findViewById(R.id.appbar);
                //pestanas = (TabLayout) vista.findViewById(R.id.tabs); //new TabLayout(getActivity());
                pestanas.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
                appBar.addView(pestanas);

                // Setear adaptador al viewpager.
                viewPager = (ViewPager) vista.findViewById(R.id.viewPagerCreaMas);
                poblarViewPager(viewPager);
                viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                        Constantes.ultimo_layout_panel_creamas_pos=viewPager.getCurrentItem();
                    }
                });
                viewPager.setCurrentItem(Constantes.ultimo_layout_panel_creamas_pos);
                pestanas.setupWithViewPager(viewPager);
            }
            pestanas.setTabGravity(TabLayout.GRAVITY_FILL);
            Constantes.ultimo_layout=R.id.viewPagerCreaMas;
        }
        return vista;
    }


    private void poblarViewPager(ViewPager viewPager) {
        SeccionesInformacionAdapter adapter = new SeccionesInformacionAdapter(getFragmentManager());
        adapter.addFragment(new CreamasQueesFragment(),"Información");
       // adapter.addFragment(new RuedaNegociosFragment(),"Información");
        adapter.addFragment(new ProyectosCreamasFragment(),"Proyectos");//getString(R.string.titulo_creamas_objetivos)
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (vista.findViewById(R.id.contenedorFragmentCreamasPortrait)!=null || vista.findViewById(R.id.contenedorFragmentCreamasLandscape)!=null){
            if (savedInstanceState!=null){
                Constantes.rotacionInfoCreamas=1;
            }
        }

        if (Constantes.rotacionInfoCreamas==0){
            appBar.removeView(pestanas);
        }
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
            //interfaceComunicacionFragments=(IComunicaFragments) this.actividad;
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
