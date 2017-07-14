package co.quindio.sena.recyclerviewpersonalizado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<PersonajeVo> listaPersonajes;
    RecyclerView recyclerPersonajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        construirRecycler();

    }

    private void llenarPersonajes() {
        listaPersonajes.add(new PersonajeVo("Krusty","Herschel Shmoikel Pinkus Yerocham Krustofsky, conocido como Krusty el payaso, es un personaje de la serie de dibujos animados Los Simpson.",R.drawable.krusti));
        listaPersonajes.add(new PersonajeVo("Homero","Homer Jay Simpson es uno de los protagonistas de la serie. Es el padre de la familia protagonista y uno de los personajes centrales y más importantes de la serie.",R.drawable.homero));
        listaPersonajes.add(new PersonajeVo("Marge","Marjorie \"Marge\" Bouvier Simpson una de los protagonistas de la serie. Es la esposa de Homer Simpson y madre de los tres hijos que ha tenido de esta unión amorosa: Bart, Lisa y Maggie. ",R.drawable.marge));
        listaPersonajes.add(new PersonajeVo("Bart","Bartholomew JoJo «Bart» Simpson, es uno de los protagonistas de la serie. Bart tiene 10 años y es el primogénito, y único hijo varón de Homer y Marge Simpson. ",R.drawable.bart));
        listaPersonajes.add(new PersonajeVo("Lisa","Lisa Marie Simpson es una de las protagonistas de la serie. Es la hija mediana de Homer y Marge Simpson, y hermana de Bart y Maggie. Goza de notable protagonismo y complejidad en la serie.",R.drawable.lisa));
        listaPersonajes.add(new PersonajeVo("Magie","Margaret Evelyn \"Maggie\" Simpson es una de las protagonistas de la serie. Es la tercera hija del matrimonio protagonista, Homer y Marge Simpson, y la más joven de ellos.",R.drawable.magie));
        listaPersonajes.add(new PersonajeVo("Flanders","Nedward «Ned» Flanders es un personaje ficticio de la serie de televisión de dibujos animados Los Simpson. La voz original en inglés es de Harry Shearer.",R.drawable.flanders));
        listaPersonajes.add(new PersonajeVo("Milhouse","Milhouse Mussolini Van Houten es un personaje ficticio de la serie animada Los Simpson, creado por Matt Groening.",R.drawable.milhouse));
        listaPersonajes.add(new PersonajeVo("Mr. Burns","Charles Montgomery Burns, más conocido como el señor Burns o Monty Burns, es un personaje ficticio recurrente de la serie de televisión de dibujos animados Los Simpson, creada por Matt Groening.",R.drawable.burns));
    }

    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnList: Utilidades.visualizacion=Utilidades.LIST;
                break;
            case R.id.btnGrid: Utilidades.visualizacion=Utilidades.GRID;
                break;
        }
        construirRecycler();
    }

    private void construirRecycler() {
        listaPersonajes=new ArrayList<>();
        recyclerPersonajes= (RecyclerView) findViewById(R.id.RecyclerId);

        if (Utilidades.visualizacion==Utilidades.LIST){
            recyclerPersonajes.setLayoutManager(new LinearLayoutManager(this));
        }else {
            recyclerPersonajes.setLayoutManager(new GridLayoutManager(this,3));
        }

        llenarPersonajes();

        AdaptadorPersonajes adapter=new AdaptadorPersonajes(listaPersonajes);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        "Selección: "+listaPersonajes.get
                                (recyclerPersonajes.getChildAdapterPosition(view))
                                .getNombre(),Toast.LENGTH_SHORT).show();
            }
        });

        recyclerPersonajes.setAdapter(adapter);
    }
}
