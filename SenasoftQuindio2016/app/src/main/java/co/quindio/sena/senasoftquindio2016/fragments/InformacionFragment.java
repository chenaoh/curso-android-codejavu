package co.quindio.sena.senasoftquindio2016.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
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
 * {@link InformacionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InformacionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InformacionFragment extends Fragment {
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
    int val=1;
    Bundle savedInstanceState;

    private AppBarLayout appBar;
    private TabLayout pestanas;
    private ViewPager viewPager;

    public InformacionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InformacionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InformacionFragment newInstance(String param1, String param2) {
        InformacionFragment fragment = new InformacionFragment();
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
        Constantes.rotacionInformacion=0;
        this.savedInstanceState=savedInstanceState;
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_informacion, container, false);
        if (savedInstanceState == null) {
            View parent = (View) container.getParent();
            if (appBar==null){
                appBar = (AppBarLayout) parent.findViewById(R.id.appbar);
                pestanas = new TabLayout(getActivity());
                //appBar = (AppBarLayout) vista.findViewById(R.id.appbar);
                //pestanas = (TabLayout) vista.findViewById(R.id.tabs); //new TabLayout(getActivity());
                pestanas.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#FFFFFF"));
                pestanas.setTabGravity(TabLayout.GRAVITY_FILL);
                appBar.addView(pestanas);

                // Setear adaptador al viewpager.
                viewPager = (ViewPager) vista.findViewById(R.id.viewPagerInformacion);
                poblarViewPager(viewPager);
                viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                        Constantes.ultimo_layout_panel_info_pos=viewPager.getCurrentItem();
                    }
                });
                viewPager.setCurrentItem(Constantes.ultimo_layout_panel_info_pos);
                pestanas.setupWithViewPager(viewPager);
            }
            pestanas.setTabGravity(TabLayout.GRAVITY_FILL);
            Constantes.ultimo_layout=R.id.viewPagerInformacion;
        }
        return vista;
    }

    private void poblarViewPager(ViewPager viewPager) {
        SeccionesInformacionAdapter adapter = new SeccionesInformacionAdapter(getFragmentManager());
        adapter.addFragment(new QueEsFragment(),getString(R.string.item_informacion_que_es));
        adapter.addFragment(new ParticipantesFragment(),getString(R.string.item_informacion_dirigido));
        adapter.addFragment(new LugarFragment(), getString(R.string.item_informacion_lugar));

        viewPager.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (vista.findViewById(R.id.contenedorFragmentInformacionPortrait)!=null || vista.findViewById(R.id.contenedorFragmentInformacionLandscape)!=null){
            if (savedInstanceState!=null){
                Constantes.rotacionInformacion=1;
            }
        }
        if (Constantes.rotacionInformacion==0){
            appBar.removeView(pestanas);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            val=0;
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            val=1;

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
