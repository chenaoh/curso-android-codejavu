package co.example.chenao.operacionesmatematicas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText campo1,campo2;
    TextView etiResultado;
    int num1,num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campo1= (EditText) findViewById(R.id.campo1);
        campo2= (EditText) findViewById(R.id.campo2);
        etiResultado= (TextView) findViewById(R.id.etiResultado);
    }

    public void onClick(View view) {

        num1= Integer.parseInt(campo1.getText().toString()) ;
        num2= Integer.parseInt(campo2.getText().toString()) ;

        switch (view.getId()){
            case R.id.btnSuma: sumar();
                break;
            case R.id.btnResta: restar();
                break;
            case R.id.btnMulti: multiplicar();
                break;
            case R.id.btnDiv: dividir();
                break;
        }

    }

    private void sumar() {
        int suma=num1+num2;
        etiResultado.setText("El Resultado de la suma es: "+suma);
    }

    private void restar() {
        int resta=num1-num2;
        etiResultado.setText("El Resultado de la resta es: "+resta);
    }

    private void multiplicar() {
        int mult=num1*num2;
        etiResultado.setText("El Resultado de la multiplicacion es: "+mult);
    }

    private void dividir() {

        if(num2>0){
            int div=num1/num2;
            etiResultado.setText("El Resultado de la division es: "+div);
        }else{
            Toast.makeText(this,"Advertencia",Toast.LENGTH_LONG).show();
            etiResultado.setText("El numero 2 debe mayor a 0 para la division");
        }


    }
}
