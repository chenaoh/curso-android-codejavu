package co.quindio.sena.pruebadesdecero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MensajeActivity extends AppCompatActivity {

    TextView msj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje);

        msj= (TextView) findViewById(R.id.txtNombreMsj);

        Bundle miBundle=this.getIntent().getExtras();

        if (miBundle!=null){

            String nombre=miBundle.getString("nombre");
            String msj_string=getString(R.string.text_msj_bienvenido);
            msj.setText(msj_string+"  "+nombre);
        }


    }

    public void onclick(View view) {
        finish();
    }
}
