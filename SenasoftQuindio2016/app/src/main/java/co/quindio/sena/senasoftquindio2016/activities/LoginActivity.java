package co.quindio.sena.senasoftquindio2016.activities;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import co.quindio.sena.senasoftquindio2016.MainActivity;
import co.quindio.sena.senasoftquindio2016.MapaEventoActivity;
import co.quindio.sena.senasoftquindio2016.R;
import co.quindio.sena.senasoftquindio2016.dialogos.RegistroDialog;
import co.quindio.sena.senasoftquindio2016.interfaces.IRegistroListener;

public class LoginActivity extends AppCompatActivity implements IRegistroListener{

  //  ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
     //   pDialog=new ProgressDialog(this);
      //  pDialog.setMessage("...");
    }

    public void onClick(View view) {
        Intent miIntent=null;

        switch (view.getId()){
            case R.id.btnGooglePlus:
                miIntent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(miIntent);
                finish();
                break;
            case R.id.btnRegistrate:
                RegistroDialog dialogoRegistro=new RegistroDialog();
                dialogoRegistro.show(getSupportFragmentManager(),"DialogoRegistro");
                break;
            case R.id.btnFacebook:

                break;
            case R.id.btnTwitter:
                break;
        }
       //
    }

    @Override
    public void registroUsuarioApp(String nombre, String correo) {

      /* pDialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent miIntent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(miIntent);
                pDialog.hide();
                finish();
            }
        },3000);*/
        Intent miIntent=new Intent(LoginActivity.this,MainActivity.class);
        startActivity(miIntent);
        finish();
    }
}
