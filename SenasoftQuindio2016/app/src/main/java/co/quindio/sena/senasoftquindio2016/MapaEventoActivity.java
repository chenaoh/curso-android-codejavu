package co.quindio.sena.senasoftquindio2016;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaEventoActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_evento);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ImageView imgAtras= (ImageView) findViewById(R.id.flechaAtras);
        imgAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        /*
        coordenadas:
        centro de convenciones armenia -> 4.561432, -75.650535
        Sena Centro de comercio y turismo -> 4.541068, -75.668230
        Sena Centro Agroindustria -> 4.5698656,-75.6410247
        Sena Escuela de Gastronomia del Quindio-> 4.568197, -75.641989
        Sena Centro para el desarrollo tecnologico de la construcción y la Industria -> 4.572548, -75.640005
        Escuela nacional del cafe -> 4.570552, -75.642537
         */


        // Add a marker in Sydney and move the camera
        LatLng armenia = new LatLng(4.535092, -75.673156);
        mMap.addMarker(new MarkerOptions().position(armenia).title("Armenia Quindio"));

        LatLng centroComercioTurismo = new LatLng(4.541068, -75.668230);
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource
                (R.mipmap.icono_sena)).anchor(0.0f, 1.0f).position(centroComercioTurismo).title("Centro de comercio y turismo Sede Galán"));

        LatLng centroConstruccion = new LatLng(4.572548, -75.640005);
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource
                (R.mipmap.icono_sena)).anchor(0.0f, 1.0f).position(centroConstruccion).title("Centro para el desarrollo técnologico de la construcción y la Industria"));

        LatLng centroAgroIndustria = new LatLng(4.5698656, -75.6410247);
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource
                (R.mipmap.icono_sena)).anchor(0.0f, 1.0f).position(centroAgroIndustria).title("Centro Agroindustrial"));

        LatLng centroGastronomia = new LatLng(4.568197, -75.641989);
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource
                (R.mipmap.icono_sena)).anchor(0.0f, 1.0f).position(centroGastronomia).title("Escuela de Gastronomia del Quindio"));

        LatLng centroDeConvenciones = new LatLng(4.561432, -75.650535);
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource
                (R.mipmap.icono_centro_convenciones)).anchor(0.0f, 1.0f).position(centroDeConvenciones).title("Centro de Convenciones"));

        LatLng escuelaNacionalDelCafe = new LatLng(4.570552, -75.642537);
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource
                (R.mipmap.icono_sena)).anchor(0.0f, 1.0f).position(escuelaNacionalDelCafe).title("Escuela Nacional del Café"));


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centroDeConvenciones, 13));
    }
}
