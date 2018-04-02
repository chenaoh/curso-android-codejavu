package co.quindio.sena.senasoftquindio2016.dialogos;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import co.quindio.sena.senasoftquindio2016.MainActivity;
import co.quindio.sena.senasoftquindio2016.R;
import co.quindio.sena.senasoftquindio2016.interfaces.IRegistroListener;

/**
 * Created by CHENAO on 6/08/2016.
 */
public class RegistroDialog extends DialogFragment {

    /**Declaramos la interfaz para el envio de datos a la actividad contenedora*/
    IRegistroListener interfaceRegistroListener;

    EditText campoNombre,campoCorreo;
    TextView mensajeValidacion;

    public RegistroDialog() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createLoginDialogo();
    }

    /**
     * Crea un diálogo con personalizado para comportarse
     * como formulario de login
     *
     * @return Diálogo
     */
    public AlertDialog createLoginDialogo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_signin, null);

        builder.setView(v);

        Button registro = (Button) v.findViewById(R.id.btnRegistro);
        campoNombre= (EditText) v.findViewById(R.id.campoNombre);
        campoCorreo= (EditText) v.findViewById(R.id.campoCorreo);
        mensajeValidacion=(TextView) v.findViewById(R.id.msjValidacion);


        registro.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // Toast.makeText(getContext(), "Registro", Toast.LENGTH_SHORT).show();
                         if(validaFormulario()){
                            //Enviamos el dato a la actividad
                            interfaceRegistroListener.registroUsuarioApp(campoNombre.getText().toString(),campoCorreo.getText().toString());
                            dismiss();
                        }
                    }
                }
        );
        return builder.create();
    }

    private boolean validaFormulario() {
        campoNombre.setError(null);
        campoCorreo.setError(null);
        mensajeValidacion.setText("");

        String nombre=campoNombre.getText().toString();
        String correo=campoCorreo.getText().toString();
        boolean retorno;

        if (!TextUtils.isEmpty(nombre) && !TextUtils.isEmpty(correo)) {
            retorno = true;

            if (!isNombreValid(nombre)){
              //  mensajeValidacion.setText("Por favor ingrese su nombre completo");
                campoNombre.setError("El Nombre es muy corto");
                retorno = false;
            }else if (!isEmailValid(correo)){
                //mensajeValidacion.setText("el correo es invalido");
                campoCorreo.setError("El correo no es Valido");
                retorno = false;
            }

        }else{

            if (TextUtils.isEmpty(nombre) && TextUtils.isEmpty(correo)) {
                campoNombre.setError("El Nombre es requerido");
                campoCorreo.setError("El Correo es requerido");
            }else{
                if (TextUtils.isEmpty(nombre)) {
                    //mensajeValidacion.setText("el campo nombre es requerido");
                    campoNombre.setError("El Nombre es requerido");
                    campoCorreo.setError(null);
                } else if (TextUtils.isEmpty(correo)) {
                   // mensajeValidacion.setText("el campo correo es requerido");
                    campoCorreo.setError("El Correo es requerid");
                    campoNombre.setError(null);
                }
            }

            retorno = false;
        }

        if (retorno==false){
            mensajeValidacion.setText("Diligencie el formulario para continuar");
        }

        return retorno;
    }

    private boolean isNombreValid(String nombre) {
        //TODO: Replace this with your own logic
        return nombre.length() > 8;
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    /**
     * Comprobar que la actividad ha implementado la interfaz
     * @param activity
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            interfaceRegistroListener = (IRegistroListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(
                    activity.toString() +
                            " no implementó IRegistroListener");

        }
    }



}
